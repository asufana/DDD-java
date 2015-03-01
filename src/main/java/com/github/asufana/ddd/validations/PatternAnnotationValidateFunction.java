package com.github.asufana.ddd.validations;

import java.lang.reflect.*;

import org.apache.commons.lang3.*;

import com.github.asufana.ddd.*;
import com.github.asufana.ddd.annotations.*;
import com.github.asufana.ddd.exceptions.*;
import com.github.asufana.ddd.functions.*;

public class PatternAnnotationValidateFunction {
    
    public static <T extends AbstractValueObject> void validate(final T vo) {
        //get field
        final Field field = ReflectionUtil.getValueField(vo);
        if (field == null) {
            return;
        }
        
        //get regex pattern
        final String regexPatten = getRegexPatten(field);
        if (StringUtils.isEmpty(regexPatten)) {
            return;
        }
        
        //get value
        final String value = getFieldValue(vo, field);
        if (StringUtils.isEmpty(value)) {
            return;
        }
        
        //validate
        if (value.matches(regexPatten) == false) {
            throw new ValueObjectException(String.format("入力が正しくありません。入力値：%s, 既定値：%s",
                                                         value,
                                                         regexPatten));
        }
    }
    
    private static String getRegexPatten(final Field field) {
        final Pattern annotation = field.getAnnotation(Pattern.class);
        return annotation != null
                ? annotation.regexp()
                : null;
    }
    
    private static <T extends AbstractValueObject> String getFieldValue(final T vo,
                                                                        final Field field) {
        if (field.getType().equals(String.class) == false) {
            return null;
        }
        
        try {
            field.setAccessible(true);
            return (String) field.get(vo);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            return null;
        }
    }
    
}
