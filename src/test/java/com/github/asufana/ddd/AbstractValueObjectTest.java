package com.github.asufana.ddd;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import com.github.asufana.ddd.share.*;

public class AbstractValueObjectTest {
    
    @Test
    public void testEquals() {
        //nullと値の評価
        assertThat(new T.VoNullableFalse(null).equals(new T.VoNullableFalse("foo")),
                   is(false));
        //異なる値の評価
        assertThat(new T.VoNullableFalse("foo").equals(new T.VoNullableFalse("bar")),
                   is(false));
        //同じ値の評価
        assertThat(new T.VoNullableFalse("bar").equals(new T.VoNullableFalse("bar")),
                   is(true));
    }
    
}
