package com.example.demo.model;

public class FieldDTO {

    private ProjectMember assignee;

    private ProjectMember reporter;

    private Project project;

    public FieldDTO(ProjectMember assignee , ProjectMember reporter , Project project){

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
