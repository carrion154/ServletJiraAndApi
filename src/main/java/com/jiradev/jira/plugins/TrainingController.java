package com.jiradev.jira.plugins;

import java.io.IOException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;


@Scanned
@Path("form-data")
public class TrainingController {
  @ComponentImport
  @Inject
  private TrainingService trainingService;
  
  @POST
  @Path("create")
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response saveData(@Valid FormDataRequest request) throws IOException {

	    //logger.info(json);
	    //FormDataRequest request;

	    //FormDataRequest request = objectMapper.readValue(json, FormDataRequest.class);
	    FormData saved = trainingService.save(request.getIssueKey(), request.getProject());

	    return Response.ok().build();
	}
}
