package ch.heArc.hotelDiscovery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ch.heArc.hotelDiscovery.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	private final UserService userService;
	
	@Autowired
	public SecurityConfig(UserService userService) {
		this.userService = userService;
	}
	
	

	
	/*@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		// authentication manager (see below)
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("nicolas.laoun@he-arc.ch").password(encoder.encode("nico"))
				.roles("ADMIN");
	}*/

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// http builder configurations for authorize requests and form login (see below)
		/*http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/admin/**").hasRole("ADMIN")
	      .antMatchers("/anonymous*").anonymous()
	      .antMatchers("/signin*").permitAll()
	      .antMatchers("/login*").permitAll()
	      .anyRequest().authenticated()
	      .and()
	      .formLogin()
	      .loginPage("/signin")
	      .loginProcessingUrl("/perform_login")
	      .defaultSuccessUrl("/home", true)
	      .failureUrl("/error")
	      //.failureHandler(authenticationFailureHandler())
	      .and()
	      .logout()
	      .logoutUrl("/perform_logout")
	      .deleteCookies("JSESSIONID");
	      //.logoutSuccessHandler(logoutSuccessHandler());*/
		http        
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            //.anyRequest().authenticated()
            //.anyRequest().hasRole("USER")
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers("/user").hasRole("USER")
            .antMatchers("/hello").hasAnyRole("USER", "ADMIN")
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
        .logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
        	.deleteCookies("JSESSIONID");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("test configuration");
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.userDetailsService(userService)
				.passwordEncoder(encoder);
	}

}
