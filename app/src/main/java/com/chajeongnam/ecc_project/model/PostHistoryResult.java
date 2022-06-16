package com.chajeongnam.ecc_project.model;

import java.io.Serializable;

public class PostHistoryResult implements Serializable {
    private String content;
    private String description;
    private int id;
    private int score;

    public PostHistoryResult() {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostHistoryResult(String content, int id, String title_id, int score, String description) {
        this.content = content;
        this.id = id;
        this.score = score;
        this.description=description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
//test12