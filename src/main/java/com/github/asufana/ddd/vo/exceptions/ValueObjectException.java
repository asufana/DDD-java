package com.github.asufana.ddd.vo.exceptions;

import java.lang.reflect.*;

public class ValueObjectException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private final String message;
    
    public static ValueObjectException overLengthException(final Object o,
                                                           final Field field) {
        return new ValueObjectException(String.format("ValueObjectException: %s: %s#%s",
                                                      "文字列が超過しています",
                                                      o.getClass()
                                                       .getSimpleName(),
                                                      field.getName()));
    }
    
    public static ValueObjectException nullException(final Object o,
                                                     final Field field) {
        return new ValueObjectException(String.format("ValueObjectException: %s: %s#%s",
                                                      "値が設定されていません",
                                                      o.getClass()
                                                       .getSimpleName(),
                                                      field.getName()));
    }
    
    public ValueObjectException(final String message) {
        super();
        this.message = String.format("ValueObjectException: %s", message);
    }
    
    public ValueObjectException(final Exception ex) {
        super(ex);
        message = String.format("ValueObjectException: %s", ex.getMessage());
    }
    
    @Override
    public String toString() {
        return message;
    }
}
