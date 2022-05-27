package com.chajeongnam.ecc_project.model;

public class Result {
    String score;
    String description;

    public Result(String score, String description) {
        this.score = score;
        this.description = description;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
