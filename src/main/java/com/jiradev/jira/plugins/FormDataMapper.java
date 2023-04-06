package com.jiradev.jira.plugins;


public class FormDataMapper {

	  public static FormDataDTO map(FormData formData)  {
		  
		 FormDataDTO dto = new FormDataDTO();
		 
		 dto.setIdIssue(formData.getIssue());
		 dto.setIdProject(formData.getProject());

	     return dto;
			

	  }

	}
