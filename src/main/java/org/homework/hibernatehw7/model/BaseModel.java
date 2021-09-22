package org.homework.hibernatehw7.model;

import java.io.Serializable;

@FunctionalInterface
public interface BaseModel<ID> extends Serializable {
    ID getId();
}
