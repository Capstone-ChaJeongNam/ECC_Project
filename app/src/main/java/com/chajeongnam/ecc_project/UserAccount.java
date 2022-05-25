package com.chajeongnam.ecc_project;

public class UserAccount {

    private String idToken;
    private String emailId;
    private String password;
    private String name;
    private String birth;

    public UserAccount() { }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserAccount(String idToken, String emailId, String password, String name, String birth){
        this.idToken = idToken;
        this.emailId = emailId;
        this.password = password;
        this.name = name;
        this.birth = birth;
    }
}
