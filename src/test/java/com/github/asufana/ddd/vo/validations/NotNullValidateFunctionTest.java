package com.github.asufana.ddd.vo.validations;

import org.junit.*;

import com.github.asufana.ddd.vo.exceptions.*;
import com.github.asufana.ddd.vo.share.*;
import com.github.asufana.ddd.vo.share.T.SomeEntity;

public class NotNullValidateFunctionTest {
    
    @Test
    public void testValidate() throws Exception {
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
        
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupDirectAnnotationNullableTrue(1));
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupDirectAnnotationNullableFalse(1));
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupDirectAnnotationMix(1, 1));
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
    
    @Test
    public void testValidateDirectAnnotationNullableTrue() throws Exception {
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupDirectAnnotationNullableTrue(null));
    }
    
    @Test(expected = ValueObjectException.class)
    public void testValidateDirectAnnotationNullableFalse() throws Exception {
        //例外が発生すること
        NotNullValidateFunction.validate(new T.VoGroupDirectAnnotationNullableFalse(null));
    }
    
    @Test(expected = ValueObjectException.class)
    public void testValidateDirectAnnotationMix() throws Exception {
        //例外が発生すること
        NotNullValidateFunction.validate(new T.VoGroupDirectAnnotationMix(null,
                                                                          null));
    }
    
    @Test
    public void testValidateManyToOneAnnotation() throws Exception {
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupManyToOne(new SomeEntity()));
    }
    
    @Test(expected = EntityException.class)
    public void testValidateManyToOneAnnotationException() throws Exception {
        //例外が発生すること
        NotNullValidateFunction.validate(new T.VoGroupManyToOne(null));
    }
    
    @Test
    public void testValidateManyToOneAnnotationWithNotFound() throws Exception {
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupManyToOneWithNotFoundAnnotation(new SomeEntity()));
    }
    
    @Test
    public void testValidateManyToOneAnnotationWithNotFoundException() throws Exception {
        //例外が発生しないこと
        NotNullValidateFunction.validate(new T.VoGroupManyToOneWithNotFoundAnnotation(null));
    }
    
}
