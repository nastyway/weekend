package com.nastyway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
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
    excludeFilters=@ComponentScan.Filter(Configuration.class)
)
public class WebConfig extends WebMvcConfigurerAdapter // ���ͼ��͸� �߰��ϱ� ���� WebMvcConfigurerAdapter �� ����Ѵ�
{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
		registry.addResourceHandler("/base/**").addResourceLocations("/base/");
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
     * ���ͼ��� �߰�
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new CorsInterceptor());
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
    /*
    *//**
     * ���Ͼ��ε� ������ ����
     *//*
    @Bean
    public CommonsMultipartResolver multipartResolver() {
    	CommonsMultipartResolver multipartresolver = new CommonsMultipartResolver();
    	multipartresolver.setMaxUploadSize(52428800); // 50MB ����
    	multipartresolver.setDefaultEncoding("UTF-8");
    	return multipartresolver;
    }*/
}
