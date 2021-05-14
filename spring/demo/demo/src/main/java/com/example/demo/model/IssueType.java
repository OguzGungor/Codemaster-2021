package com.example.demo.model;

public class IssueType {

    private String id;

    private String description;

    private String name;

    private boolean subtask;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubtask(boolean subtask) {
        this.subtask = subtask;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean getSubtask(){
        return subtask;
    }
}
