package org.kshrd.configuration;

import java.util.Locale;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
@MapperScan("org.kshrd.repositories")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * My batis Configuration
	 */
	private DataSource dataSource;
	
	@Autowired
	public MvcConfiguration(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception{
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);;
		return sessionFactoryBean;
	}
	
	
	/**
	 * some important Configuration
	 * > i18n Configuration 
	 */
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		/*
		 *  Map a view controller to the given URL path (or pattern) in other 
		 *  to render a response with a pre-configured status code and view.
		 */
		registry.addViewController("/").setViewName("/admin/dsahboard");
		
		
		registry.addViewController("/student-cu").setViewName("/student-cu");
		
		registry.addViewController("/admin").setViewName("/admin/dashboard");
		registry.addViewController("/admin/").setViewName("/admin/dashboard");
		registry.addViewController("/admin/dashboard").setViewName("/admin/dashboard");
		registry.addViewController("/admin/user-list").setViewName("/admin/user-list");
		registry.addViewController("/admin/detail").setViewName("/admin/detail");
		registry.addViewController("/admin/user-cu").setViewName("/admin/user-cu");
		registry.addViewController("/admin/role-list").setViewName("/admin/role-list");
		registry.addViewController("/admin/role-cu").setViewName("/admin/role-cu");

		/*
		 * Map a simple controller to the given URL path (or pattern) in order to
		 * set the response status to the given code without rendering a body.
		 */
		registry.addStatusController("/status", HttpStatus.BAD_REQUEST);
		
		/*
		 * Map a view controller to the given URL path (or pattern) in other 
		 * to redirect to another URL.
		 */
		registry.addRedirectViewController("to-home", "/home");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/*
		 * Static Resources store in the project
		 */
		registry.addResourceHandler("/resources/**")
					.addResourceLocations("classpath:/static/");
		/*
		 * Static Resources store outside the project
		 */
		registry.addResourceHandler("/files/**")
					.addResourceLocations("file:/opt/FILES_MANAGEMENT/images/");
	}

	
	
	/*
	 * Internalization i18n
	 */
	
	/*
	 * In order for our application to be able to determine which locale is
	 * currently being used, we need to add a LocaleResolver bean:
	 */
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("Spring_Locale");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}

	/*
	 * Next, we need to add an interceptor bean that will switch to a new locale
	 * based on the value of the lang parameter appended to a request:
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/*
	 * In order to take effect, this bean needs to be added to the application’s
	 * interceptor registry.
	 *
	 * To achieve this, our @Configuration class has to extend the
	 * WebMvcConfigurerAdapter class and override the addInterceptors() method:
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/*
	 * Defining the Message Sources
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/i18n/messages/message");
		messageSource.setCacheSeconds(0);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
