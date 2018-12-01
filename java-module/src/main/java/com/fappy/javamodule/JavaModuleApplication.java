package com.fappy.javamodule;

import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import com.fappy.javamodule.service.UserService;

@SpringBootApplication
@EnableJpaRepositories("com.fappy.javamodule.repository")
@ComponentScan
@EnableTransactionManagement
public class JavaModuleApplication extends SpringBootServletInitializer implements WebApplicationInitializer, CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
    private DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaModuleApplication.class, args);
	}
	
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+2:00"));
	}
	
	@Override
    public void run(String... args) throws Exception {
        System.out.println("Our DataSource is = " + this.dataSource);
		System.out.println("Well starting ...");
    }
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(this.userService);
	}
}
