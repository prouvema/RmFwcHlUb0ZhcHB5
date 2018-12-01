package com.fappy.javamodule.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
//@EnableTransactionManagement
//@ComponentScan(basePackages = { "com.springhibernatesample.dao" })
@PropertySource({ "classpath:database.properties" })
public class DataAccessConfig {
//
//	@Autowired
//	private Environment env;
//
//	@Bean
//	public DataSource dataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//		dataSource.setUrl(env.getProperty("jdbc.url"));
//		dataSource.setUsername(env.getProperty("jdbc.username"));
//		dataSource.setPassword(env.getProperty("jdbc.password"));
//		return dataSource;
//	}

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
//		dataSource.setUrl(environment.getProperty("jdbc.url"));
//		dataSource.setUsername(environment.getProperty("jdbc.user"));
//		dataSource.setPassword(environment.getProperty("jdbc.pass"));
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));

		return dataSource;
	}

//
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setDatabase(Database.ORACLE);
//		vendorAdapter.setGenerateDdl(false);
//		vendorAdapter.setShowSql(Boolean.TRUE);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setPackagesToScan("com.springhibernatesample.model");
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setDataSource(dataSource());
//
//		Properties jpaProperties = new Properties();
//		jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//		jpaProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//		jpaProperties.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
//		jpaProperties.put("hibernate.connection.isolation", env.getProperty("hibernate.connection.isolation"));
//		jpaProperties.put("hibernate.connection.autoReconnect", env.getProperty("hibernate.connection.autoReconnect"));
//		jpaProperties.put("hibernate.connection.autoReconnectForPools",
//				env.getProperty("hibernate.connection.autoReconnectForPools"));
//
//		factory.setJpaProperties(jpaProperties);
//		return factory;
//	}
//
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.fappy.javamodule.domain.entity" });
		em.setPersistenceUnitName("lmsPU");
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(environment.getProperty("hibernate.show_sql", Boolean.class));
		jpaVendorAdapter.setDatabasePlatform(environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		return jpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

		return hibernateProperties;
	}

	@Bean
	public TransactionTemplate transactionTemplate() {
		return new TransactionTemplate(transactionManager());
	}
}