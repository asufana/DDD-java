package com.github.asufana.ddd.entity.validations;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.persistence.*;

import org.junit.*;

import com.github.asufana.ddd.entity.*;

public class NotNullValidationFunctionTest {
    
    @Test
    public void test() throws Exception {
        new SomeEntity(1, 2, 3);
    }
    
    @Test
    public void testSomeEntityNullException() throws Exception {
        try {
            new SomeEntity(1, 2, null);
            fail();
        }
        catch (final Exception e) {
            assertThat(e.toString(),
                       is("ValueObjectException: ValueObjectException: 値が設定されていません: SomeEntity#someId"));
        }
    }
    
    @Test
    public void testBaseEntityNullException() throws Exception {
        try {
            new SomeEntity(1, null, 3);
            fail();
        }
        catch (final Exception e) {
            assertThat(e.toString(),
                       is("ValueObjectException: ValueObjectException: 値が設定されていません: SomeEntity#baseId"));
        }
    }
    
    @Test
    public void testBaseBaseEntityNullException() throws Exception {
        try {
            new SomeEntity(null, 2, 3);
            fail();
        }
        catch (final Exception e) {
            assertThat(e.toString(),
                       is("ValueObjectException: ValueObjectException: 値が設定されていません: SomeEntity#baseBaseId"));
        }
    }
    
    public static class SomeEntity extends BaseEntity {
        @Column(nullable = false)
        public final Integer someId;
        
        public SomeEntity(final Integer baseBaseId,
                final Integer baseId,
                final Integer someId) {
            super(baseBaseId, baseId);
            this.someId = someId;
            isSatisfied();
        }
        
        @Override
        public void isSatisfied() {
            notNull();
        }
    }
    
    public static abstract class BaseEntity extends BaseBaseEntity {
        @Column(nullable = false)
        public final Integer baseId;
        
        public BaseEntity(final Integer baseBaseId, final Integer baseId) {
            super(baseBaseId);
            this.baseId = baseId;
        }
        
        @Override
        public void isSatisfied() {}
    }
    
    public static abstract class BaseBaseEntity extends AbstractEntity<BaseBaseEntity> {
        @Column(nullable = false)
        public final Integer baseBaseId;
        
        public BaseBaseEntity(final Integer baseBaseId) {
            this.baseBaseId = baseBaseId;
        }
        
        @Override
        public void isSatisfied() {}
    }
}
