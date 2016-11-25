package org.example;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.example.doit.Command;
import org.example.doit.CreateEvent;
import org.example.doit.EventDao;
import org.example.doit.JacksonJsonSerializer;
import org.example.doit.JpaEventDao;
import org.example.doit.JsonSerializer;
import org.example.doit.ListEvent;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan
@EnableTransactionManagement
@EnableWebMvc
public class OrganizerApplicationContext {

	@Value("${db.driver}")
	private String dbDriverClassName;

	@Value("${db.url}")
	private String dbUrl;

	@Value("${db.username}")
	private String dbUserName;

	@Value("${db.password}")
	private String dbPassword;
	
	@Bean
	public DataSource dataSource(){
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(dbDriverClassName);
		config.setJdbcUrl(dbUrl);
		config.setUsername(dbUserName);
		config.setPassword(dbPassword);
		
		return new HikariDataSource(config);
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPersistenceUnitName("persistenceUnit");
		emf.setPersistenceProvider(new HibernatePersistenceProvider());
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		emf.setPackagesToScan("org.example.organizer.model");
		emf.afterPropertiesSet();
		return emf.getObject();		
	}
	
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter jpaVendorAdapapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapapter.setGenerateDdl(true);
		return jpaVendorAdapapter;
	}
	
	@Bean
	public PlatformTransactionManager txManager(){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		return jpaTransactionManager;
	}
	
	@Bean(name="create-event")
	public Command createEvent(){
		Command command = new CreateEvent();
		return command;
	}
	
	@Bean(name="list-event")
	public Command listEvent(){
		Command command = new ListEvent();
		return command;
	}
	
	@Bean
	public JsonSerializer myserializer(){
		return new JacksonJsonSerializer();
	}
	
	@Bean
	public EventDao eventDao(){
		return new JpaEventDao();
	}
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		return viewResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource res = new ResourceBundleMessageSource();
		res.setBasename("validation");
		return res;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placehoder(){
		PropertySourcesPlaceholderConfigurer placeholder = new PropertySourcesPlaceholderConfigurer();
		return placeholder;
	}
}
