package com.nastyway.weekend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * MVC ������ Ŭ����.
 * �� Ŭ������ �������� sevlet-context.xml �� ������ ����Ѵ�.
 * @author mj
 *
 */
@Configuration
@EnableWebMvc
@EnableAsync // @Async ������̼��� ����ϱ� ����
@ComponentScan(
    basePackages="com.nethru.test",
    excludeFilters=@ComponentScan.Filter(Configuration.class)
)
public class WebConfig extends WebMvcConfigurerAdapter // ���ͼ��͸� �߰��ϱ� ���� WebMvcConfigurerAdapter �� ����Ѵ�
{
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
}
