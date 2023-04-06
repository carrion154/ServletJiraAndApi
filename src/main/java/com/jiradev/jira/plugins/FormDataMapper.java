package com.jiradev.jira.plugins;


public class FormDataMapper {

	  public static FormDataDTO map(FormData formData)  {

	     return new FormDataDTO()
			.setIdProject(formData.getProject())
			.setIssueKey(formData.getIssueKey());

	  }

	}
