package com.github.asufana.ddd.vo.functions;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.*;

import javax.persistence.*;

import org.junit.*;

import com.github.asufana.ddd.vo.*;

public class ToStringFunctionTest {
    
    @Test
    public void testStringVo() {
        final VoString vo = new VoString("x");
        final Field field = ReflectionUtil.getValueField(vo);
        final String value = ToStringFunction.toStringByValueField(vo, field);
        assertThat(value, is("x"));
    }
    
    @Test
    public void testIntgerVo() {
        final VoInteger vo = new VoInteger(1);
        final Field field = ReflectionUtil.getValueField(vo);
        final String value = ToStringFunction.toStringByValueField(vo, field);
        assertThat(value, is("1"));
    }
    
    public static class VoString extends AbstractValueObject {
        @Column(nullable = false)
        private final String value;
        
        public VoString(final String value) {
            this.value = value;
        }
    }
    
    public static class VoInteger extends AbstractValueObject {
        @Column(nullable = false)
        private final Integer value;
        
        public VoInteger(final Integer value) {
            this.value = value;
        }
    }
}
