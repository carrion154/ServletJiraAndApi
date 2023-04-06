package com.jiradev.jira.plugins.jira.tabpanels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.ofbiz.core.entity.GenericEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.plugin.projectpanel.ProjectTabPanel;
import com.atlassian.jira.plugin.projectpanel.impl.AbstractProjectTabPanel;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.browse.BrowseContext;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleActors;
import com.atlassian.jira.security.roles.ProjectRoleManager;


public class UserRoleProjectTabPanel extends AbstractProjectTabPanel implements ProjectTabPanel
{
    private static final Logger log = LoggerFactory.getLogger(UserRoleProjectTabPanel.class);
    private ProjectRoleManager projectRoleManager = ComponentAccessor.getComponent(ProjectRoleManager.class);
    private TreeMap people = new TreeMap();
    private final ProjectManager  projectManager = ComponentAccessor.getProjectManager();
    private final IssueManager issueManager = ComponentAccessor.getIssueManager();

    @Override
    public Map createVelocityParams (BrowseContext ctx)
    {
    	log.debug("start create velocity");
        //Get the params object. This will hold all the values that can be accessed in the user-role-project-tab.properties file
        Map params = super.createVelocityParams(ctx);
        //Get the project object
        Project project = ctx.getProject();
        //Get all the project roles
        Collection<ProjectRole> projectRoles = projectRoleManager.getProjectRoles();
        //Iterate through each role and get the users associated with the role
        for (ProjectRole projectRole : projectRoles){
            ProjectRoleActors roleActors = projectRoleManager.getProjectRoleActors(projectRole, project);
            people.put(projectRole.getName(),roleActors.getUsers());
        }
        
        List<Project> projectList = projectManager.getProjects();
        
        List<String> projects = projectList.stream()
        .map(Project::getName)
        .collect(Collectors.toList());
        
        Collection<Long> issueIds = new ArrayList<>();
        
        for (Project proj:projectList) {
        	try {
        		issueIds.addAll(issueManager.getIssueIdsForProject(proj.getId()));
				
			} catch (GenericEntityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        List<Issue> issues = issueManager.getIssueObjects(issueIds);
        params.put("projects", projects);
        params.put("issues", issues);
        params.put("people",people);
        params.put("avatarService",ComponentAccessor.getAvatarService());
        return params;
    }

    @Override
    public boolean showPanel(BrowseContext context)
    {
        return true;
    }
    
   
}