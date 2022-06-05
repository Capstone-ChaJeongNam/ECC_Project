package com.chajeongnam.ecc_project.model;

public class Result {
    Integer score;
    String description;
    String content;


    public Result(Integer score, String description, String content) {
        this.score = score;
        this.description = description;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
