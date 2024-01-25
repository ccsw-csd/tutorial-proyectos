package com.ccsw.tutorialbatch.model;

public class Category {

    private String name;
    private String type;
    private String characteristics;

    public Category() {
    }

    public Category(String name, String type, String characteristics) {
        this.name = name;
        this.type = type;
        this.characteristics = characteristics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "Category [name=" + getName() + ", type=" + getType() + ", characteristics=" + getCharacteristics() + "]";
    }

}