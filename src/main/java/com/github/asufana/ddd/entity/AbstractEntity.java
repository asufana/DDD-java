package com.github.asufana.ddd.entity;

import javax.persistence.*;

import org.hibernate.proxy.*;

import com.github.asufana.ddd.entity.exceptions.*;
import com.github.asufana.ddd.entity.functions.AbstractCallback.CallbackType;
import com.github.asufana.ddd.entity.functions.*;
import com.github.asufana.ddd.vo.validations.*;

@MappedSuperclass
public abstract class AbstractEntity<T extends EnhancedPlayGenericModel> extends EnhancedPlayGenericModel {
    
    //重複確認
    protected void notDuplicate(final T other, final String message) {
        if (other != null && !equals(other)) {
            throw EntityException.duplicateException(message);
        }
    }
    
    //値オブジェクトnullチェック
    protected void notNull(final Object entity) {
        NotNullValidateFunction.validate(entity);
    }
    
    protected boolean sameIdentityAs(final T other) {
        if (id() == null) {
            return false;
        }
        return other != null && id().equals(other.id());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            //参考：http://stackoverflow.com/questions/11013138/hibernate-equals-and-proxy
            if (guessClass(other).isAssignableFrom(guessClass(this)) == false
                    && guessClass(this).isAssignableFrom(guessClass(other)) == false) {
                return false;
            }
        }
        return sameIdentityAs((T) other);
    }
    
    private Class<?> guessClass(final Object obj) {
        //HibernateによりJavassistで修正された元エンティティのクラス名取得
        //参考：http://stackoverflow.com/questions/1139611/loading-javassist-ed-hibernate-entity
        return obj instanceof HibernateProxy
                ? HibernateProxyHelper.getClassWithoutInitializingProxy(obj)
                : obj.getClass();
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @PostLoad
    /** Executed after an entity has been loaded into the current persistence context or an entity has been refreshed. */
    protected void afterLoad() {
        CallbackInvokeFunction.invoke(this, CallbackType.afterLoad);
    }
    
    @PrePersist
    /** Executed before the entity manager persist operation is actually executed or cascaded. This call is synchronous with the persist operation. */
    protected void beforeSave() {
        CallbackInvokeFunction.invoke(this, CallbackType.beforeSave);
    }
    
    @PostPersist
    /** Executed after the entity manager persist operation is actually executed or cascaded. This call is invoked after the database INSERT is executed. */
    protected void afterSave() {
        CallbackInvokeFunction.invoke(this, CallbackType.afterSave);
    }
    
    @PreUpdate
    /** Executed before the database UPDATE operation. */
    protected void beforeUpdate() {
        CallbackInvokeFunction.invoke(this, CallbackType.beforeUpdate);
    }
    
    @PostUpdate
    /** Executed after the database UPDATE operation. */
    protected void afterUpdate() {
        CallbackInvokeFunction.invoke(this, CallbackType.afterUpdate);
    }
    
    @PreRemove
    /** Executed before the entity manager remove operation is actually executed or cascaded. This call is synchronous with the remove operation. */
    protected void beforeDelete() {
        CallbackInvokeFunction.invoke(this, CallbackType.beforeDelete);
    }
    
    @PostRemove
    /** Executed after the entity manager remove operation is actually executed or cascaded. This call is synchronous with the remove operation. */
    protected void afterDelete() {
        CallbackInvokeFunction.invoke(this, CallbackType.afterDelete);
    }
    
}
