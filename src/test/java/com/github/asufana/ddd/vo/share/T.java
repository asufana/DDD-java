package com.github.asufana.ddd.vo.share;

import javax.persistence.*;

import com.github.asufana.ddd.entity.*;
import com.github.asufana.ddd.vo.*;

public class T {
    
    //nullable=true
    public static class VoGroupNullableTrue extends AbstractValueObject {
        @SuppressWarnings("unused")
        private final VoNullableTrue vo01;
        @SuppressWarnings("unused")
        private final VoNullableTrue vo02;
        
        public VoGroupNullableTrue(final VoNullableTrue vo01,
                final VoNullableTrue vo02) {
            this.vo01 = vo01;
            this.vo02 = vo02;
        }
    }
    
    //nullable=false
    public static class VoGroupNullableFalse extends AbstractValueObject {
        @SuppressWarnings("unused")
        private final VoNullableFalse vo01;
        @SuppressWarnings("unused")
        private final VoNullableFalse vo02;
        
        public VoGroupNullableFalse(final VoNullableFalse vo01,
                final VoNullableFalse vo02) {
            this.vo01 = vo01;
            this.vo02 = vo02;
        }
    }
    
    //no @Column annotation
    public static class VoGroupNoColumnAnnotation extends AbstractValueObject {
        @SuppressWarnings("unused")
        private final VoNoColumnAnnotation vo01;
        @SuppressWarnings("unused")
        private final VoNoColumnAnnotation vo02;
        
        public VoGroupNoColumnAnnotation(final VoNoColumnAnnotation vo01,
                final VoNoColumnAnnotation vo02) {
            this.vo01 = vo01;
            this.vo02 = vo02;
        }
    }
    
    //mix
    public static class VoGroupMix extends AbstractValueObject {
        @SuppressWarnings("unused")
        private final VoNullableTrue vo01;
        @SuppressWarnings("unused")
        private final VoNullableFalse vo02;
        @SuppressWarnings("unused")
        private final VoNoColumnAnnotation vo03;
        
        public VoGroupMix(final VoNullableTrue vo01,
                final VoNullableFalse vo02,
                final VoNoColumnAnnotation vo03) {
            this.vo01 = vo01;
            this.vo02 = vo02;
            this.vo03 = vo03;
        }
    }
    
    //------------------------------------------------
    
    //direct annotation: nullable=true
    public static class VoGroupDirectAnnotationNullableTrue extends AbstractValueObject {
        @Column(nullable = true)
        private final Integer someValue;
        
        public VoGroupDirectAnnotationNullableTrue(final Integer someValue) {
            this.someValue = someValue;
        }
    }
    
    //direct annotation: nullable=false
    public static class VoGroupDirectAnnotationNullableFalse extends AbstractValueObject {
        @Column(nullable = false)
        private final Integer someValue;
        
        public VoGroupDirectAnnotationNullableFalse(final Integer someValue) {
            this.someValue = someValue;
        }
    }
    
    //direct annotation: nullable=mix
    public static class VoGroupDirectAnnotationMix extends AbstractValueObject {
        @Column(nullable = true)
        private final Integer someValueTrue;
        
        @Column(nullable = false)
        private final Integer someValueFalse;
        
        public VoGroupDirectAnnotationMix(final Integer someValueTrue,
                final Integer someValueFalse) {
            this.someValueTrue = someValueTrue;
            this.someValueFalse = someValueFalse;
        }
    }
    
    //------------------------------------------------
    
    public static class VoGroupManyToOne extends AbstractValueObject {
        @ManyToOne
        private SomeEntity someEntity;
        
        public VoGroupManyToOne() {}
        
        public VoGroupManyToOne(final SomeEntity someEntity) {
            this.someEntity = someEntity;
        }
    }
    
    public static class SomeEntity extends AbstractEntity<SomeEntity> {
        @Override
        public void isSatisfied() {}
    }
    
    //------------------------------------------------
    
    //nullable=false
    public static class VoNullableFalse extends AbstractValueObject {
        @Column(nullable = false)
        private final String value;
        
        public VoNullableFalse(final String value) {
            this.value = value;
        }
    }
    
    //nullable=true
    public static class VoNullableTrue extends AbstractValueObject {
        @Column(nullable = true)
        private final String value;
        
        public VoNullableTrue(final String value) {
            this.value = value;
        }
    }
    
    //no @Column annotation
    public static class VoNoColumnAnnotation extends AbstractValueObject {
        @SuppressWarnings("unused")
        private final String value;
        
        public VoNoColumnAnnotation(final String value) {
            this.value = value;
        }
    }
    
}
