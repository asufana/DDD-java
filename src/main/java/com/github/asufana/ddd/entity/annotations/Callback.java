package com.github.asufana.ddd.entity.annotations;

import java.lang.annotation.*;

import com.github.asufana.ddd.entity.functions.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Callback {
    
    public Class<? extends AbstractCallback<?>> value();
    
}
