package com.github.asufana.ddd.validations;

import java.lang.reflect.*;
import java.util.*;

import javax.persistence.*;

import com.github.asufana.ddd.exceptions.*;
import com.github.asufana.ddd.functions.*;

public class NotNullValidateFunction {
    
    public static void validate(final Object vo) {
        final List<Field> fields = ReflectionUtil.getValueObjectFields(vo);
        for (final Field field : fields) {
            final Field valueField = ReflectionUtil.getValueField(field);
            if (valueField == null) {
                continue;
            }
            final Column column = valueField.getDeclaredAnnotation(Column.class);
            if (column != null
                    && column.nullable() == false
                    && isNull(vo, field)) {
                throw ValueObjectException.nullException(field);
            }
        }
    }
    
    private static boolean isNull(final Object vo, final Field field) {
        try {
            final Object object = field.get(vo);
            return object == null;
        }
        catch (IllegalArgumentException | IllegalAccessException e) {}
        return false;
    }
    
}
