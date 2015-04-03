package com.github.asufana.ddd.vo.functions;

import java.lang.reflect.*;

import org.apache.commons.lang3.builder.*;

import com.github.asufana.ddd.vo.*;

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
                    ? object.toString()
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
