package com.github.asufana.ddd.entity;

import org.junit.*;

import com.github.asufana.ddd.entity.annotations.*;
import com.github.asufana.ddd.entity.functions.*;

public class AbstractCallbackTest {
    
    @Test
    public void test() throws Exception {
        final SomeEntity someEntity = new SomeEntity("x");
        someEntity.beforeSave();
    }
    
    @Callback(SomeEntityCallback.class)
    public static class SomeEntity extends AbstractEntity<SomeEntity> {
        public final String value;
        
        public SomeEntity(final String value) {
            this.value = value;
        }
        
        @Override
        public void isSatisfied() {}
    }
    
    public static class SomeEntityCallback extends AbstractCallback<SomeEntity> {
        
        @Override
        public void beforeSave(final SomeEntity entity) {
            System.out.println("beforeSave:" + entity.value);
        }
    }
}
