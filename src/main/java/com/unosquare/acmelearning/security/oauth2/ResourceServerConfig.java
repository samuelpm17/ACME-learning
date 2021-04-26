package com.unosquare.acmelearning.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private JwtTokenStore tokenStore;
	
	@Autowired
	public ResourceServerConfig(JwtTokenStore tokenStore) {
		this.tokenStore = tokenStore;
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/oauth/token").permitAll()
		.antMatchers("/h2-console/**").permitAll()
        .antMatchers(HttpMethod.POST, "/courses/**").hasRole("INSTRUCTOR")
        .antMatchers(HttpMethod.PUT, "/courses/**").hasRole("INSTRUCTOR")
        .antMatchers(HttpMethod.DELETE, "/courses/**").hasRole("INSTRUCTOR")
        .antMatchers("/instructors/**").hasRole("INSTRUCTOR")
        .antMatchers("/courses/list").hasRole("STUDENT")
		//.antMatchers(HttpMethod.GET,"/api/users/**").hasAnyRole("ADMIN","USER")
		.anyRequest().authenticated();
		
		http.headers().frameOptions().disable();
	}
}
