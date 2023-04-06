package com.jiradev.jira.plugins;

import javax.inject.Inject;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

@Scanned
public class TrainingService {
    @ComponentImport
    @Inject
    private ActiveObjects ao;

    //private final EntityManager entityManager = ao.create(FormData.class).getEntityManager();

    public FormData save(String issueKey, String project) {
        FormData formData = ao.create(FormData.class);

        formData.setIssue(issueKey);
        formData.setProject(project);
        formData.save();
        //ao.find(FormData.class,net.java.ao.Query.select().where("PROJECT=?","HelpDesk SD"));
        return formData;
    }
}
