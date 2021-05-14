package com.example.demo.model;

import java.util.List;

public class IssueList {

    private Issue[]issues;

    private long total;

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setIssues(Issue[] issues) {
        this.issues = issues;
    }

    public Issue[] getIssues() {
        return issues;
    }
}
