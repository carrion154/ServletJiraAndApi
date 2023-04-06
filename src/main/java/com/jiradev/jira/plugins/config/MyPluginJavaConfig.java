package com.jiradev.jira.plugins.config;

import com.jiradev.jira.plugins.TrainingController;
import com.jiradev.jira.plugins.TrainingService;
import com.jiradev.jira.plugins.api.MyPluginComponent;
import com.jiradev.jira.plugins.impl.MyPluginComponentImpl;
import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugins.osgi.javaconfig.configs.beans.ModuleFactoryBean;
import com.atlassian.plugins.osgi.javaconfig.configs.beans.PluginAccessorBean;
import com.atlassian.sal.api.ApplicationProperties;
import org.osgi.framework.ServiceRegistration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.exportOsgiService;
import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService;

@Configuration
@Import({
        ModuleFactoryBean.class,
        PluginAccessorBean.class
})
public class MyPluginJavaConfig {

	// creates a new ActiveObjects bean
	@Bean
	public ActiveObjects activeObjects() {
	    return importOsgiService(ActiveObjects.class);
	}
    // imports ApplicationProperties from OSGi
    @Bean
    public ApplicationProperties applicationProperties() {
        return importOsgiService(ApplicationProperties.class);
    }

    @Bean
    public MyPluginComponent myPluginComponent(ApplicationProperties applicationProperties) {
        return new MyPluginComponentImpl(applicationProperties);
    }
    
    @Bean
    public TrainingService trainingService() {return new TrainingService();}
    
    @Bean
    public TrainingController trainingController() {return new TrainingController();} 
    
    // Exports MyPluginComponent as an OSGi service
    @Bean
    public FactoryBean<ServiceRegistration> registerMyDelegatingService(
            final MyPluginComponent mypluginComponent) {
        return exportOsgiService(mypluginComponent, null, MyPluginComponent.class);
    }
}