package com.github.asufana.ddd.functions;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.*;

import org.junit.*;

import com.github.asufana.ddd.share.*;

public class ToStringFunctionTest {
    
    @Test
    public void testToStringByValueField() {
        final T.VoNullableFalse vo = new T.VoNullableFalse("x");
        final Field field = ReflectionUtil.getValueField(vo);
        final String value = ToStringFunction.toStringByValueField(vo, field);
        assertThat(value, is("x"));
    }
}
