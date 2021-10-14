package com.abhi.learning.spring.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
/* default user config (given in application.yml) will not work now because we are overriding default implementation */
public class AuthConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${myapp.password}")
	private String pwd;
	
	//@Autowired
	//private DataSource dataSource;
	
	/*InMemory authentication, no db */
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = getPasswordEncoder();
		auth.inMemoryAuthentication().withUser("akp").password(passwordEncoder.encode("secret")).roles("ADMIN").and()
				.withUser("akp2").password(passwordEncoder.encode("secret2")).roles("GUEST");
	}*/
	
	/*create schema programmatically, h2 DB authentication, nothing is needed in application.yml 
	Requirement- h2 in pom and below method thats it.
	Note- if other DB is used, for that DB details are needed in appication.yml
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = getPasswordEncoder();
		System.out.println("Clear Pwd: "+ pwd);
		String pass = passwordEncoder.encode(pwd);
		System.out.println("Encoded Pwd: "+ pass);
		auth.jdbcAuthentication()
		.dataSource(dataSource)// dataSource created by spring boot by looking h2 in pom dependency, no details needed in application.yml
		.withDefaultSchema() // schema will be created by spring with user and role table, with following user details
		.withUser(
				 User.withUsername("akp")
				.password(passwordEncoder.encode(pwd))
				.roles("ADMIN","GUEST")
				)
		.withUser(
				 User.withUsername("akp2")
				.password(passwordEncoder.encode("secret2"))
				.roles("GUEST")
				);
	}*/
	
	/*Create schema using schema.sql, data.sql in resources directory- feature of spring boot
	  nothing needed in application.yml for h2 db
	  work with any DB, for other than h2, DB details are needed in application.yml
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
	   .jdbcAuthentication()
	   .dataSource(dataSource);
	} */
	
	
	/* JPA authentication , no out of the box impl, instead we need to implement UserDetailsService & UserDetails interfaces.
	 * UserDetailsService can use JPA or any other technology to get user details */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userName -> new MyUserDetails(userName));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").hasAnyRole("ADMIN")
		.antMatchers("/guest").hasAnyRole("ADMIN", "GUEST")
		.antMatchers("/").permitAll()
		.and()
		.formLogin();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2A);
	}

	public static void main(String[] args) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		encoder = new BCryptPasswordEncoder(BCryptVersion.$2A);
		System.out.println(encoder.encode("secret3"));
	}
}
