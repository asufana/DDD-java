package com.github.asufana.ddd.vo.functions;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.util.*;

import org.junit.*;

import com.github.asufana.ddd.vo.*;
import com.github.asufana.ddd.vo.share.*;

public class ReflectionUtilTest {
    
    @Test
    public void testGetValueFieldByInstance() {
        final Field field = ReflectionUtil.getValueField(new T.VoNullableFalse("x"));
        assertThat(field.getName(), is("value"));
    }
    
    @Test
    public void testGetValueFieldByField() throws Exception {
        final Field field = ReflectionUtil.getValueField(new T.VoNullableFalse("x"));
        final Field valueField = ReflectionUtil.getValueField(field);
        assertThat(valueField.getName(), is("value"));
    }
    
    @Test
    public void testGetValueObjects() throws Exception {
        final List<AbstractValueObject> vos = ReflectionUtil.getValueObjects(new T.VoGroupNullableFalse(new T.VoNullableFalse("x"),
                                                                                                        new T.VoNullableFalse("x")));
        assertThat(vos.size(), is(2));
    }
    
    @Test
    public void testGetValueObjectFields() throws Exception {
        final List<Field> fields = ReflectionUtil.getValueObjectFields(new T.VoGroupNullableFalse(new T.VoNullableFalse("x"),
                                                                                                  new T.VoNullableFalse("x")));
        assertThat(fields.size(), is(2));
    }
    
    @Test
    public void testGetColumnAnnotationFields() throws Exception {
        final List<Field> fields = ReflectionUtil.getColumnAnnotationFields(new T.VoGroupDirectAnnotationMix(null,
                                                                                                             null));
        assertThat(fields.size(), is(2));
    }
}
