package com.github.asufana.ddd.entity.functions;

import com.github.asufana.ddd.entity.*;

public abstract class AbstractCallback<T extends AbstractEntity<?>> {
    
    public static enum CallbackType {
        afterLoad,
        beforeSave,
        afterSave,
        beforeUpdate,
        afterUpdate,
        beforeDelete,
        afterDelete;
    }
    
    public void afterLoad(final T entity) {}
    
    public void beforeSave(final T entity) {}
    
    public void afterSave(final T entity) {}
    
    public void beforeUpdate(final T entity) {}
    
    public void afterUpdate(final T entity) {}
    
    public void beforeDelete(final T entity) {}
    
    public void afterDelete(final T entity) {}
    
}
