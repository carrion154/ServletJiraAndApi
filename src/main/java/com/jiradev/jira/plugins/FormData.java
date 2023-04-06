package com.jiradev.jira.plugins;
import net.java.ao.Entity;
import net.java.ao.Preload;
import net.java.ao.schema.Table;

@Preload
@Table("form_data")
public interface FormData extends Entity {
   String getProject();
   String getIssue();
   
   void setProject(String project);
   void setIssue(String issue);
}
