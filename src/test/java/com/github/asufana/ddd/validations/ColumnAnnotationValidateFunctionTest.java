package com.github.asufana.ddd.validations;

import java.math.*;

import javax.persistence.*;

import org.joda.time.*;
import org.junit.*;

import com.github.asufana.ddd.*;
import com.github.asufana.ddd.exceptions.*;

public class ColumnAnnotationValidateFunctionTest {
    
    // String.class -----------------------------------------
    
    //null test
    @Test
    public void testValidateionNullableString01() throws Exception {
        //例外とならないこと
        ColumnAnnotationValidateFunction.validate(new StringVo(null));
    }
    
    //null test
    @Test
    public void testValidateionNullableString02() throws Exception {
        //例外とならないこと
        ColumnAnnotationValidateFunction.validate(new StringVo(""));
    }
    
    static class StringVo extends AbstractValueObject {
        @Column
        public final String value;
        
        public StringVo(final String value) {
            this.value = value;
        }
    }
    
    // String.class with @Column -----------------------------------------
    
    //null test
    @Test(expected = ValueObjectException.class)
    public void testValidateionNullableString03() throws Exception {
        //例外となること
        ColumnAnnotationValidateFunction.validate(new StringVoWithColumnAttr(null));
    }
    
    //null test
    @Test(expected = ValueObjectException.class)
    public void testValidateionNullableString04() throws Exception {
        //例外となること
        ColumnAnnotationValidateFunction.validate(new StringVoWithColumnAttr(""));
    }
    
    //length test
    @Test(expected = ValueObjectException.class)
    public void testValidateionLength() throws Exception {
        //例外となること
        ColumnAnnotationValidateFunction.validate(new StringVoWithColumnAttr("aaaaaaaaaaaaa"));
    }
    
    static class StringVoWithColumnAttr extends AbstractValueObject {
        @Column(nullable = false, length = 4)
        public final String value;
        
        public StringVoWithColumnAttr(final String value) {
            this.value = value;
        }
    }
    
    // Integer.class -----------------------------------------
    
    //null test
    @Test
    public void testValidateionNullableInteger01() throws Exception {
        //例外とならないこと
        ColumnAnnotationValidateFunction.validate(new IntegerVo(null));
    }
    
    static class IntegerVo extends AbstractValueObject {
        @Column
        public final Integer value;
        
        public IntegerVo(final Integer value) {
            this.value = value;
        }
    }
    
    // Integer.class with @Column -----------------------------------------
    
    //null test
    @Test(expected = ValueObjectException.class)
    public void testValidateionNullableInteger02() throws Exception {
        //例外となること
        ColumnAnnotationValidateFunction.validate(new IntegerVoWithColumnAttr(null));
    }
    
    static class IntegerVoWithColumnAttr extends AbstractValueObject {
        @Column(nullable = false)
        public final Integer value;
        
        public IntegerVoWithColumnAttr(final Integer value) {
            this.value = value;
        }
    }
    
    // Long.class -----------------------------------------
    
    //null test
    @Test
    public void testValidateionNullableLong01() throws Exception {
        //例外とならないこと
        ColumnAnnotationValidateFunction.validate(new LongVo(null));
    }
    
    static class LongVo extends AbstractValueObject {
        @Column
        public final Long value;
        
        public LongVo(final Long value) {
            this.value = value;
        }
    }
    
    // Long.class with @Column -----------------------------------------
    
    //null test
    @Test(expected = ValueObjectException.class)
    public void testValidateionNullableLong02() throws Exception {
        //例外となること
        ColumnAnnotationValidateFunction.validate(new LongVoWithColumnAttr(null));
    }
    
    static class LongVoWithColumnAttr extends AbstractValueObject {
        @Column(nullable = false)
        public final Long value;
        
        public LongVoWithColumnAttr(final Long value) {
            this.value = value;
        }
    }
    
    // BigDecimal.class -----------------------------------------
    
    //null test
    @Test
    public void testValidateionNullableBigDecimal01() throws Exception {
        //例外とならないこと
        ColumnAnnotationValidateFunction.validate(new BigDecimalVo(null));
    }
    
    static class BigDecimalVo extends AbstractValueObject {
        @Column
        public final BigDecimal value;
        
        public BigDecimalVo(final BigDecimal value) {
            this.value = value;
        }
    }
    
    // BigDecimal.class with @Column -----------------------------------------
    
    //null test
    @Test(expected = ValueObjectException.class)
    public void testValidateionNullableBigDecimal02() throws Exception {
        //例外となること
        ColumnAnnotationValidateFunction.validate(new BigDecimalVoWithColumnAttr(null));
    }
    
    static class BigDecimalVoWithColumnAttr extends AbstractValueObject {
        @Column(nullable = false)
        public final BigDecimal value;
        
        public BigDecimalVoWithColumnAttr(final BigDecimal value) {
            this.value = value;
        }
    }
    
    // DateTime.class -----------------------------------------
    
    //null test
    @Test
    public void testValidateionNullableDateTime01() throws Exception {
        //例外とならないこと
        ColumnAnnotationValidateFunction.validate(new DateTimeVo(null));
    }
    
    static class DateTimeVo extends AbstractValueObject {
        @Column
        public final DateTime value;
        
        public DateTimeVo(final DateTime value) {
            this.value = value;
        }
    }
    
    // DateTime.class with @Column -----------------------------------------
    
    //null test
    @Test(expected = ValueObjectException.class)
    public void testValidateionNullableDateTime02() throws Exception {
        //例外となること
        ColumnAnnotationValidateFunction.validate(new DateTimeVoWithColumnAttr(null));
    }
    
    static class DateTimeVoWithColumnAttr extends AbstractValueObject {
        @Column(nullable = false)
        public final DateTime value;
        
        public DateTimeVoWithColumnAttr(final DateTime value) {
            this.value = value;
        }
    }
    
    // Boolean.class -----------------------------------------
    
    //null test
    @Test
    public void testValidateionNullableBoolean01() throws Exception {
        //例外とならないこと
        ColumnAnnotationValidateFunction.validate(new BooleanVo(null));
    }
    
    static class BooleanVo extends AbstractValueObject {
        @Column
        public final Boolean value;
        
        public BooleanVo(final Boolean value) {
            this.value = value;
        }
    }
    
    // Boolean.class with @Column -----------------------------------------
    
    //null test
    @Test(expected = ValueObjectException.class)
    public void testValidateionNullableBoolean02() throws Exception {
        //例外となること
        ColumnAnnotationValidateFunction.validate(new BooleanVoWithColumnAttr(null));
    }
    
    static class BooleanVoWithColumnAttr extends AbstractValueObject {
        @Column(nullable = false)
        public final Boolean value;
        
        public BooleanVoWithColumnAttr(final Boolean value) {
            this.value = value;
        }
    }
}
