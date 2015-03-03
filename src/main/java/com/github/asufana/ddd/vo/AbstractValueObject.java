package com.github.asufana.ddd.vo;

import org.apache.commons.lang3.builder.*;

import com.github.asufana.ddd.vo.functions.*;
import com.github.asufana.ddd.vo.validations.*;

public abstract class AbstractValueObject {
    
    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, other);
    }
    
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    @Override
    public String toString() {
        return ToStringFunction.toString(this);
    }
    
    protected void validate() {
        ColumnAnnotationValidateFunction.validate(this);
        PatternAnnotationValidateFunction.validate(this);
        NotNullValidateFunction.validate(this);
    }
}
