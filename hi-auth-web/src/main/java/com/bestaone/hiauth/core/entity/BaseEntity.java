package com.bestaone.hiauth.core.entity;

import java.io.Serializable;

public abstract class BaseEntity<PK extends Serializable> {

    public abstract PK getId();
    public abstract void setId(PK id);

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

}
