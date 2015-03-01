package com.github.asufana.ddd.validations;

import org.junit.*;

import com.github.asufana.ddd.exceptions.*;
import com.github.asufana.ddd.share.*;

public class NotNullValidateFunctionTest {
    
    @Test
    public void testValidateNotNullValue() throws Exception {
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoNullableTrue("x"));
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoNullableFalse("x"));
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoNoColumnAnnotation("x"));
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupNullableTrue(new T.VoNullableTrue("x"),
                                                                   new T.VoNullableTrue("x")));
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupNullableFalse(new T.VoNullableFalse("x"),
                                                                    new T.VoNullableFalse("x")));
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupNoColumnAnnotation(new T.VoNoColumnAnnotation("x"),
                                                                         new T.VoNoColumnAnnotation("x")));
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupMix(new T.VoNullableTrue("x"),
                                                          new T.VoNullableFalse("x"),
                                                          new T.VoNoColumnAnnotation("x")));
    }
    
    @Test
    public void testValidateNullableTrue() throws Exception {
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupNullableTrue(null, null));
    }
    
    @Test(expected = ValueObjectException.class)
    public void testValidateNullableFalse() throws Exception {
        //例外が発生すること
        NotNullValidateFunction.validate(new T.VoGroupNullableFalse(null, null));
    }
    
    @Test
    public void testValidateNoColumnAnnotation() throws Exception {
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupNoColumnAnnotation(null,
                                                                         null));
    }
    
    @Test(expected = ValueObjectException.class)
    public void testValidateMix() throws Exception {
        //例外が発生すること
        NotNullValidateFunction.validate(new T.VoGroupMix(null, null, null));
    }
}
