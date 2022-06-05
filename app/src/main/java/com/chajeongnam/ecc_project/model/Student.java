package com.chajeongnam.ecc_project.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String name;
    private String birth;
    private String gender;
    private String grade;
    private String attrClass;
    private String uid;
    private String recent="";



    public Student(String name, String birth, String gender, String grade, String attrClass, String uid) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.grade = grade;
        this.uid = uid;
        this.attrClass = attrClass;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getRecent() {
        return recent;
    }

    public void setRecent(String recent) {
        this.recent = recent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.birth);
        parcel.writeString(this.gender);
        parcel.writeString(this.grade);
        parcel.writeString(this.attrClass);
        parcel.writeString(this.uid);
        parcel.writeString(this.recent);

    }

    public Student(Parcel parcel){
        this.name = parcel.readString();
        this.birth = parcel.readString();
        this.gender = parcel.readString();
        this.grade = parcel.readString();
        this.attrClass = parcel.readString();
        this.uid = parcel.readString();
        this.recent = parcel.readString();
    }
    // create Parcelable
    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel parcel) {
            return new Student(parcel);
        }
        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
