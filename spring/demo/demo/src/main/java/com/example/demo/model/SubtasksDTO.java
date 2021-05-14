package com.example.demo.model;

import java.util.List;

public class SubtasksDTO {

    private List<IssueDTO>issues;


    public void setIssues(List<IssueDTO> issues) {
        this.issues = issues;
    }

    public List<IssueDTO> getIssues() {
        return issues;
    }
}
