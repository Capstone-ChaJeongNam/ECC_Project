package com.chajeongnam.ecc_project.model;

public class Student {
    private String name;
    private String grade;
    private String attrClass;
    private String recent;

    public Student(String name, String grade, String attrClass, String recent) {
        this.name = name;
        this.grade = grade;
        this.attrClass = attrClass;
        this.recent = recent;
    }

    public String getRecent() {
        return recent;
    }

    public void setRecent(String recent) {
        this.recent = recent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAttrClass() {
        return attrClass;
    }

    public void setAttrClass(String attrClass) {
        this.attrClass = attrClass;
    }
}
