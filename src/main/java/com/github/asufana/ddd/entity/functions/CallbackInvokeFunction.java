package com.github.asufana.ddd.entity.functions;

import java.lang.reflect.*;

import com.github.asufana.ddd.entity.*;
import com.github.asufana.ddd.entity.annotations.*;
import com.github.asufana.ddd.entity.exceptions.*;
import com.github.asufana.ddd.entity.functions.AbstractCallback.CallbackType;

public abstract class CallbackInvokeFunction {
    
    public static <T extends AbstractEntity<? extends EnhancedPlayGenericModel>> void invoke(final T entity,
                                                                                             final CallbackType callbackType) {
        final Class<? extends AbstractCallback<?>> callbackClass = getCallbackClass(entity);
        if (callbackClass == null) {
            return;
        }
        
        final AbstractCallback<?> callbackInstance = newInstance(callbackClass);
        invoke(entity, callbackClass, callbackInstance, callbackType);
    }
    
    private static <T extends AbstractEntity<? extends EnhancedPlayGenericModel>> void invoke(final T entity,
                                                                                              final Class<? extends AbstractCallback<?>> callbackClass,
                                                                                              final AbstractCallback<?> callbackInstance,
                                                                                              final CallbackType callbackType) {
        try {
            final Method method = callbackClass.getMethod(callbackType.name(),
                                                          AbstractEntity.class);
            method.invoke(callbackInstance, entity);
        }
        catch (InvocationTargetException | NoSuchMethodException
                | SecurityException | IllegalAccessException
                | IllegalArgumentException e) {
            throw new EntityException(e);
        }
    }
    
    private static AbstractCallback<?> newInstance(final Class<? extends AbstractCallback<?>> callbackClass) {
        try {
            return callbackClass.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            new EntityException(e.getMessage());
        }
        return null;
    }
    
    private static <T extends AbstractEntity<? extends EnhancedPlayGenericModel>> Class<? extends AbstractCallback<?>> getCallbackClass(final T entity) {
        final Callback callback = entity.getClass()
                                        .getDeclaredAnnotation(Callback.class);
        return callback != null
                ? callback.value()
                : null;
    }
}
