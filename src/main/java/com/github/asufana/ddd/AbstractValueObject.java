package com.github.asufana.ddd;


import org.apache.commons.lang3.builder.*;

import com.github.asufana.ddd.functions.*;
import com.github.asufana.ddd.validations.*;
import com.github.asufana.ddd.validations.ColumnAnnotationValidateFunction.FieldInfoCollection;

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
    
    FieldInfoCollection fields() {
        return ColumnAnnotationValidateFunction.fields(this);
    }
}
