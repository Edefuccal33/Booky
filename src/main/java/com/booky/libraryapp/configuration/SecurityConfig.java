package com.booky.libraryapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll().and().formLogin()
				.loginPage("/login").loginProcessingUrl("/logincheck").usernameParameter("email")
				.passwordParameter("password").defaultSuccessUrl("/loginsuccess").failureUrl("/login?error=error")
				.permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll().and().csrf()
				.disable();
	}
}
