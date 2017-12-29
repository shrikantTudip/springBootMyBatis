package com.yuktamedia.model;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer id;

    private String name;
    private String image;
    private String color;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
	public String toString() {
		return getId() + "," + getName() + "," + getImage() + "," + getColor();
	}
}
