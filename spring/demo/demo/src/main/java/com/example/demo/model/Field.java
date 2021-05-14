package com.example.demo.model;

public class Field {

     private IssueType issuetype;

     private ProjectMember assignee;

     private ProjectMember reporter;

     private Project project;

    Field(IssueType issuetype,ProjectMember assignee , ProjectMember reporter , Project project){
        this.issuetype = issuetype;
        this.assignee = assignee;
        this.project = project;
        this.reporter = reporter;
    }

    public void setAssignee(ProjectMember assignee) {
        this.assignee = assignee;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setReporter(ProjectMember reporter) {
        this.reporter = reporter;
    }

    public void setIssueType(IssueType issuetype) {
        this.issuetype = issuetype;
    }

    public IssueType getIssueType() {
        return issuetype;
    }

    public Project getProject() {
        return project;
    }

    public ProjectMember getAssignee() {
        return assignee;
    }

    public ProjectMember getReporter() {
        return reporter;
    }


}
