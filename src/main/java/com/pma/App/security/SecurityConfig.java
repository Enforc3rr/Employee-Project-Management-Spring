package com.pma.App.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username , password , enabled "+" from user_accounts where username = ?")
		.authoritiesByUsernameQuery("select username , role " + "from user_accounts where username = ?")
		.passwordEncoder(bCryptPasswordEncoder);
//		.withDefaultSchema()
//		.withUser("user")
//		.password("pass")
//		.roles("USER")
//		.and()
//		.withUser("user1")
//		.password("pass")
//		.roles("USER")
//		.and()
//		.withUser("admin")
//		.password("admin")
//		.roles("ADMIN");
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeRequests()
		.antMatchers("/project/new").hasAuthority("ADMIN")
		.antMatchers("/project/save").hasAuthority("ADMIN")
		.antMatchers("/employee/saveEmployee").hasAuthority("ADMIN")
		.antMatchers("/employee/newemployee").hasAuthority("ADMIN")
		.antMatchers("/","/**").permitAll()
		.and()
		.formLogin();
		
		httpSecurity.csrf().disable();
	}
}
