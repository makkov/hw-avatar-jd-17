package com.example.hwavatar.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Faculty {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String color;

    public Faculty() {
    }

    public Faculty(String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;

        Faculty faculty = (Faculty) o;

        if (getId() != null ? !getId().equals(faculty.getId()) : faculty.getId() != null) return false;
        if (getName() != null ? !getName().equals(faculty.getName()) : faculty.getName() != null) return false;
        return getColor() != null ? getColor().equals(faculty.getColor()) : faculty.getColor() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        return result;
    }
}
