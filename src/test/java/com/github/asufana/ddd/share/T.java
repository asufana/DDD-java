package com.github.asufana.ddd.share;

import javax.persistence.*;

import com.github.asufana.ddd.*;

public class T {
    
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
    
    public static class VoNullableFalse extends AbstractValueObject {
        @Column(nullable = false)
        private final String value;
        
        public VoNullableFalse(final String value) {
            this.value = value;
        }
    }
    
    public static class VoNullableTrue extends AbstractValueObject {
        @Column(nullable = true)
        private final String value;
        
        public VoNullableTrue(final String value) {
            this.value = value;
        }
    }
    
    public static class VoNoColumnAnnotation extends AbstractValueObject {
        @SuppressWarnings("unused")
        private final String value;
        
        public VoNoColumnAnnotation(final String value) {
            this.value = value;
        }
    }
    
}
