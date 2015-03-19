package com.github.asufana.ddd.vo.validations;

import java.lang.reflect.*;
import java.util.*;

import javax.persistence.*;

import com.github.asufana.ddd.entity.*;
import com.github.asufana.ddd.vo.*;
import com.github.asufana.ddd.vo.exceptions.*;
import com.github.asufana.ddd.vo.functions.*;

public class NotNullValidateFunction {
    
    public static <T extends AbstractEntity<?>> void validate(final T o) {
        validateByEmbeddedColumnAnnotation(o);
        validateByDirectColumnAnnotation(o);
        validateByManyToOneAnnotation(o);
    }
    
    public static <T extends AbstractValueObject> void validate(final T o) {
        validateByEmbeddedColumnAnnotation(o);
        validateByDirectColumnAnnotation(o);
        validateByManyToOneAnnotation(o);
    }
    
    private static void validateByEmbeddedColumnAnnotation(final Object o) {
        final List<Field> fields = ReflectionUtil.getValueObjectFields(o);
        for (final Field field : fields) {
            final Field valueField = ReflectionUtil.getValueField(field);
            if (valueField == null) {
                continue;
            }
            final Column column = valueField.getDeclaredAnnotation(Column.class);
            validate(o, field, column);
        }
    }
    
    private static void validateByDirectColumnAnnotation(final Object o) {
        final List<Field> fields = ReflectionUtil.getColumnAnnotationFields(o);
        for (final Field field : fields) {
            final Column column = field.getDeclaredAnnotation(Column.class);
            validate(o, field, column);
        }
    }
    
    private static void validate(final Object o,
                                 final Field field,
                                 final Column column) {
        if (column != null && column.nullable() == false && isNull(o, field)) {
            throw ValueObjectException.nullException(o, field);
        }
    }
    
    private static boolean isNull(final Object o, final Field field) {
        try {
            final Object object = field.get(o);
            return object == null;
        }
        catch (IllegalArgumentException | IllegalAccessException e) {}
        return false;
    }
    
    private static void validateByManyToOneAnnotation(final Object o) {
        final List<Field> fields = ReflectionUtil.getManyToOneAnnotationFields(o);
        for (final Field field : fields) {
            if (isNull(o, field)) {
                throw EntityException.nullException(o, field);
            }
        }
    }
}
