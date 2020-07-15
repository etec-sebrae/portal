/*package br.etec.sebrae.portal.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	        http.
	              authorizeRequests()
	              		.antMatchers(
	                          "/js/**",
	                          "/vendors/**",
	                          "/css/**",
	                          "/images/**",
	                          "/webjars/**").permitAll()
	                    .anyRequest()
	                    .authenticated()
	                    .and()
	              .formLogin()
	                          .loginPage("/login")
	                          .permitAll();
	  }
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) 
	  throws Exception {
	   builder
	    .inMemoryAuthentication()
	    .withUser("eduardo@eduardo.com").password("123456").roles("EDITOR", "ADMIN")
	    .and()
	    .withUser("fernanda").password("123456").roles("EDITOR");
	}
}*/