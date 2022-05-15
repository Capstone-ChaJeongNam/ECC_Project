package com.chajeongnam.ecc_project.model;

public class Category {
    String title;
    String area;

    public Category() {

    }

    public Category(String title, String area) {
        this.title = title;
        this.area = area;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
