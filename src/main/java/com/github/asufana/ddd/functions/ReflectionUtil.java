package com.github.asufana.ddd.functions;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import com.github.asufana.ddd.*;

public class ReflectionUtil {
    
    // get "value" field
    public static <T extends AbstractValueObject> Field getValueField(final T vo) {
        return getValueField(getFields(vo));
    }
    
    public static Field getValueField(final Field field) {
        return getValueField(getFields(field));
    }
    
    private static Field getValueField(final List<Field> fields) {
        return fields.stream()
                     .filter(field -> field.getName().equals("value"))
                     .findAny()
                     .orElse(null);
    }
    
    public static <T extends AbstractValueObject> List<AbstractValueObject> getValueObjects(final T vo) {
        return getFields(vo).stream()
                            .map(field -> {
                                field.setAccessible(true);
                                try {
                                    if (field.get(vo) instanceof AbstractValueObject) {
                                        return (AbstractValueObject) field.get(vo);
                                    }
                                }
                                catch (final Exception e) {}
                                return null;
                            })
                            .filter(valueObject -> valueObject != null)
                            .collect(Collectors.toList());
    }
    
    public static List<Field> getValueObjectFields(final Object vo) {
        return getFields(vo).stream().filter(field -> {
            field.setAccessible(true);
            return AbstractValueObject.class.isAssignableFrom(field.getType());
        }).collect(Collectors.toList());
    }
    
    private static List<Field> getFields(final Object vo) {
        return Arrays.asList(vo.getClass().getDeclaredFields());
    }
    
    private static List<Field> getFields(final Field field) {
        return Arrays.asList(field.getType().getDeclaredFields());
    }
    
}
