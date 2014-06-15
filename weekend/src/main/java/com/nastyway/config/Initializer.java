package com.nastyway.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

public class Initializer implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException
    {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
        
        this.addDispatcherServlet(servletContext);
        this.addUtf8CharacterEncodingFilter(servletContext);
        
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        
        try {
          JoranConfigurator configurator = new JoranConfigurator();
          configurator.setContext(context);
          // Call context.reset() to clear any previous configuration, e.g. default 
          // configuration. For multi-step configuration, omit calling context.reset().
          context.reset(); 
          configurator.doConfigure(new ClassPathResource("logback.xml").getInputStream());
        } catch (Exception e){
        	e.printStackTrace();
        }
        
    }
    
    /**
     * Dispatcher Servlet �� �߰��Ѵ�.
     * CORS �� �����ϰ� �ϱ� ���ؼ� dispatchOptionsRequest ������ true �� �Ѵ�.
     * @param servletContext
     */
    private void addDispatcherServlet(ServletContext servletContext)
    {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.getEnvironment().addActiveProfile("production");
        applicationContext.register(WebConfig.class);
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setInitParameter("dispatchOptionsRequest", "true"); // CORS �� ���ؼ� option request �� �޾Ƶ��δ�.
    }
    
    /**
     * UTF-8 ĳ���� ���ڵ� ���͸� �߰��Ѵ�.
     * @param servletContext
     */
    private void addUtf8CharacterEncodingFilter(ServletContext servletContext)
    {
        FilterRegistration.Dynamic filter = servletContext.addFilter("CHARACTER_ENCODING_FILTER", CharacterEncodingFilter.class);
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");
        filter.addMappingForUrlPatterns(null, false, "/*");
    }
}