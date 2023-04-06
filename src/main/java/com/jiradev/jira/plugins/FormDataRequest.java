package com.jiradev.jira.plugins;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Objects;


public class FormDataRequest implements Serializable {
    @JsonProperty("issue")
    private String issueKey;
    @JsonProperty("project")
    private String project;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormDataRequest that = (FormDataRequest) o;
        return Objects.equals(issueKey, that.issueKey) && Objects.equals(project, that.project);
    }

    @Override
    public String toString() {
        return "FormDataRequest{" +
                "issueKey='" + issueKey + '\'' +
                ", project='" + project + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueKey, project);
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public FormDataRequest(String issueKey, String project) {
        this.issueKey = issueKey;
        this.project = project;
    }

    public FormDataRequest() {
    }
}
