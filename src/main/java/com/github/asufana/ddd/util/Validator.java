package com.github.asufana.ddd.util;

import org.apache.commons.lang3.*;

public class Validator {
    
    public final Object object;
    
    public static Validator on(final Object object) {
        return new Validator(object);
    }
    
    private Validator(final Object object) {
        this.object = object;
    }
    
    public void notNull(final String fieldName) {
        if (object == null) {
            throw ValidatorException.isNull(fieldName);
        }
    }
    
    public static class ValidatorException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        
        public String message;
        
        public static ValidatorException isNull(final String fieldName) {
            final String appendMessage = StringUtils.isNotEmpty(fieldName)
                    ? " field: " + fieldName
                    : "";
            return new ValidatorException("値を入力してください" + appendMessage);
        }
        
        public ValidatorException(final String message) {
            super();
            this.message = message;
        }
    }
}
