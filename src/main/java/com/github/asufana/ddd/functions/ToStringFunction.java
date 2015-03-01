package com.github.asufana.ddd.functions;

import java.lang.reflect.*;

import org.apache.commons.lang3.builder.*;

import com.github.asufana.ddd.*;

public abstract class ToStringFunction {
    
    public static <T extends AbstractValueObject> String toString(final T vo) {
        final Field valueField = ReflectionUtil.getValueField(vo);
        return valueField != null
                ? toStringByValueField(vo, valueField)
                : toStringByReflection(vo);
    }
    
    /** valueフィールド値を返却 */
    static <T extends AbstractValueObject> String toStringByValueField(final T vo,
                                                                       final Field field) {
        try {
            field.setAccessible(true);
            final Object object = field.get(vo);
            return object != null
                    ? (String) object
                    : "";
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            return "";
        }
    }
    
    /** リフレクションでフィールド値を返却 */
    private static <T extends AbstractValueObject> String toStringByReflection(final T vo) {
        return ReflectionToStringBuilder.toString(vo,
                                                  ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
