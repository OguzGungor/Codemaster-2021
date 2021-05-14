package com.example.demo.model;

public class ProjectDTO {


    private String id;

    private String key;

    private String name;

    private int issueCount;

    public ProjectDTO(String id,String key , String name, int issueCount){
        this.id = id;
        this.key = key;
        this.name = name;
        this.issueCount = issueCount;
    }

    public String getKey() {
        return key;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIssueCount() {
        return issueCount;
    }

    public void setIssueCount(int issueCount) {
        this.issueCount = issueCount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void increment(){
        issueCount++;
    }
}
