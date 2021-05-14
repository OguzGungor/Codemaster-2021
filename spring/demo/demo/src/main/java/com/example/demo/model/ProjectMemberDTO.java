package com.example.demo.model;

public class ProjectMemberDTO {
    private String name;

    private String key;

    private String emailAddress;

    private String displayName;

    private int issueCount;

    public ProjectMemberDTO(String name, String key, String emailAddress , String displayName , int issueCount){
        this.name = name;
        this.key = key;
        this.emailAddress = emailAddress;
        this.displayName = displayName;
        this.issueCount = issueCount;
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

    public void setIssueCount(int issurCount) {
        this.issueCount = issurCount;
    }

    public int getIssueCount() {
        return issueCount;
    }

    public void incrementIssueCount(){
        issueCount++;
    }
}
