package com.example.demo.model;

public class ProjectMember {

    private String name;

    private String key;

    private String emailAddress;

    private String displayName;

    ProjectMember(String name, String key, String emailAddress , String displayName){
        this.name = name;
        this.key = key;
        this.emailAddress = emailAddress;
        this.displayName = displayName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getKey() {
        return key;
    }
}
