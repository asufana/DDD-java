package com.github.asufana.ddd.entity.exceptions;

public class EntityException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private final String message;
    
    public static EntityException duplicateException(final String message) {
        return new EntityException(String.format("EntityException: %s: %s",
                                                 "Entityが重複しています",
                                                 message));
    }
    
    public EntityException(final String message) {
        super();
        this.message = String.format("EntityException: %s", message);
    }
    
    public EntityException(final Exception ex) {
        super(ex);
        message = String.format("EntityException: %s", ex.getMessage());
    }
    
    @Override
    public String toString() {
        return message;
    }
}
