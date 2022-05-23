package com.chajeongnam.ecc_project.model;

public class History {
    Category category;
    String recent = "";
    public History(Category category, String recent) {
        this.category = category;
        this.recent = recent;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getRecent() {
        return recent;
    }

    public void setRecent(String recent) {
        this.recent = recent;
    }


}
