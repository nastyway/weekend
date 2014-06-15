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
@EnableAsync // @Async 어노테이션을 사용하기 위함
@ComponentScan(
    basePackages="com.nastyway.weekend",
    excludeFilters=@ComponentScan.Filter(Configuration.class)
)
public class WebConfig extends WebMvcConfigurerAdapter // 인터셉터를 추가하기 위해 WebMvcConfigurerAdapter 를 상속한다
{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
		registry.addResourceHandler("/base/**").addResourceLocations("/base/");
	}
	
	/**
     * 뷰 리졸버 설정
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
     * 인터셉터 추가
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new CorsInterceptor());
    }
    
    /**
     * 타일즈 설정
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
    	TilesConfigurer tc = new TilesConfigurer();
    	tc.setDefinitions(new String[] { "/WEB-INF/template/tiles.xml" });
    	tc.setCheckRefresh(true);
    	
    	return tc;
    }
    
    /**
     * 타일즈 뷰 리졸버 설정
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
     * 파일업로드 리졸버 설정
     *//*
    @Bean
    public CommonsMultipartResolver multipartResolver() {
    	CommonsMultipartResolver multipartresolver = new CommonsMultipartResolver();
    	multipartresolver.setMaxUploadSize(52428800); // 50MB 제한
    	multipartresolver.setDefaultEncoding("UTF-8");
    	return multipartresolver;
    }*/
}
