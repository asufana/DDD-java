package com.github.asufana.ddd.vo.validations;

import javax.persistence.*;

import org.junit.*;

import com.github.asufana.ddd.vo.*;
import com.github.asufana.ddd.vo.annotations.*;
import com.github.asufana.ddd.vo.exceptions.*;

public class PatternAnnotationValidateFunctionTest {
    
    @Test
    public void testValidate01() throws Exception {
        //例外発生しないこと
        PatternAnnotationValidateFunction.validate(new StringVo("A123"));
    }
    
    @Test(expected = ValueObjectException.class)
    public void testValidate02() throws Exception {
        //例外発生すること
        PatternAnnotationValidateFunction.validate(new StringVo("123A"));
    }
    
    static class StringVo extends AbstractValueObject {
        @Column
        @Pattern(regexp = "^[A-Z][0-9]{3}$")
        public final String value;
        
        public StringVo(final String value) {
            this.value = value;
        }
    }
}
