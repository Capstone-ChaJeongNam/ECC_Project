package com.chajeongnam.ecc_project.model;

public class PreChecklist {
    private int id;
    private String content;
    private String title_id;

    public PreChecklist() {}

    public PreChecklist(int id, String content,String title_id){
        this.id = id;
        this.content =content;
        this.title_id = title_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle_id() {
        return this.title_id;
    }

    public void setTitle_id(String title_id) { this.title_id = title_id; }

}
