package com.nastyway.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(
	    basePackages="com.nastyway.weekend",
	    excludeFilters=@ComponentScan.Filter(Configuration.class))
public class RootConfig {

	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	private static final String MYBATIS_CONFIG_FILE_PATH = "mybatis-config.xml";

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
//		ppc.setLocations(new Resource[] { new ClassPathResource(APP_CONFIG_FILE_PATH) });
		ppc.setLocations(new PathMatchingResourcePatternResolver().getResources("*.properties"));
		return ppc;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(this.jdbcDriverClassName);
		dataSource.setUrl(this.jdbcUrl);
		dataSource.setUsername(this.jdbcUsername);
		dataSource.setPassword(this.jdbcPassword);
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:/com/nastyway/weekend/**/mysql/*_SqlMap.xml"));
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG_FILE_PATH));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSession sqlSession() throws Exception {
		SqlSession sqlSession = new SqlSessionTemplate(this.sqlSessionFactory());
		return sqlSession;
	}

}
