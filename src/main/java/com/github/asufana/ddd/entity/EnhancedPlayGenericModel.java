package com.github.asufana.ddd.entity;

import javax.persistence.*;

import org.hibernate.annotations.*;
import org.joda.time.*;

import play.db.jpa.*;

@MappedSuperclass
public abstract class EnhancedPlayGenericModel extends GenericModel {
    
    //optimistic lock
    @Version
    private Long version;
    
    @Id
    @GeneratedValue
    protected Long id;
    
    @Column(nullable = false)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    protected DateTime createDate;
    
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    protected DateTime modifyDate;
    
    @Column(nullable = false)
    protected Integer isDisable = 0;
    
    public Long id() {
        return id;
    }
    
    public DateTime createDate() {
        return createDate;
    }
    
    public DateTime modifyDate() {
        return modifyDate;
    }
    
    public boolean isDisable() {
        return isDisable == 1;
    }
    
    public void enable() {
        isDisable = 0;
        this.save();
    }
    
    public void disable() {
        isDisable = 1;
        this.save();
    }
    
    @PrePersist
    protected void prePersist() {
        createDate = new DateTime();
    }
    
    @PreUpdate
    protected void preUpdate() {
        modifyDate = new DateTime();
    }
    
    @Override
    public <T extends JPABase> T save() {
        isSatisfied();
        return super.save();
    }
    
    /** エンティティが仕様を満たしているかどうか */
    public abstract void isSatisfied();
}
