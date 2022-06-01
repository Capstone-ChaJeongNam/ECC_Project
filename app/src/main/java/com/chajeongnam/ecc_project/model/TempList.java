package com.chajeongnam.ecc_project.model;

import java.io.Serializable;

public class TempList implements Serializable {
String title;

    public TempList(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}