package org.utcn.springproject.models;

import jakarta.persistence.Entity;

import javax.validation.constraints.Size;

@Entity
public class Tag extends AbstractEntity {

    @Size(min = 1, max = 25)
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {

    }

    public String getName() {
        return name;
    }

    public String getDisplayedName() {
        return "#" + name + " ";
    }

    public void setName(String name) {
        this.name = name;
    }
}
