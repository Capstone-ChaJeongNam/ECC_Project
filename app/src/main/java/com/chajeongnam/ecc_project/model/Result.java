package com.chajeongnam.ecc_project.model;

public class Result {
    Integer score;
    String description;

    public Result(Integer score, String description) {
        this.score = score;
        this.description = description;
    }

    public Result() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
