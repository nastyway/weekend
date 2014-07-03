package com.nastyway.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@EnableAsync // @Async ������̼��� ����ϱ� ����
@ComponentScan(
    basePackages="com.nastyway.weekend",
    excludeFilters=@ComponentScan.Filter(Configuration.class))
@MultipartConfig(fileSizeThreshold=1024*1024*5,    // 10 MB 
				maxFileSize=1024*1024*5,          // 50 MB
				maxRequestSize=1024*1024*100)      // 100 MB
public class WebConfig extends WebMvcConfigurerAdapter // ���ͼ��͸� �߰��ϱ� ���� WebMvcConfigurerAdapter �� ����Ѵ�
{
	
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
		registry.addResourceHandler("/base/**").addResourceLocations("/base/");
	}*/
	
	/**
     * @param  Set default servlet handler, this is the same as <mvc:default-servlet-handler/>
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
//		ppc.setLocations(new Resource[] { new ClassPathResource(APP_CONFIG_FILE_PATH) });
		ppc.setLocations(new PathMatchingResourcePatternResolver().getResources("*.properties"));
		return ppc;
	}
	
	/**
     * �� ������ ����
     */
    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    /**
     * Ÿ���� ����
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
    	TilesConfigurer tc = new TilesConfigurer();
    	tc.setDefinitions(new String[] { "/WEB-INF/template/tiles.xml" });
    	tc.setCheckRefresh(true);
    	
    	return tc;
    }
    
    /**
     * Ÿ���� �� ������ ����
     */
    @Bean
    public TilesViewResolver tilesViewResolver()
    {
        TilesViewResolver resolver = new TilesViewResolver();
        resolver.setViewClass(TilesView.class);
        resolver.setOrder(1);
        return resolver;
    }
    
    /**
     * ���Ͼ��ε� ������ ����
     */
    @Bean 
    public MultipartResolver multipartResolver() { 
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(); 
    	multipartResolver.setMaxUploadSize(5*1024*1024); 
    	multipartResolver.setDefaultEncoding("UTF-8");
    	return multipartResolver; 
	} 
    
    /**
     * @param  configureMessageConverters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
    	converters.add(jackson2Converter);
        super.configureMessageConverters(converters);
    }
    /*@Bean
    public StandardServletMultipartResolver multipartResolver1(){
        return new StandardServletMultipartResolver();
    }*/
}
