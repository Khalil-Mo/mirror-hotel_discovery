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
		http        
        .authorizeRequests()
        	.antMatchers("/").permitAll()
        	.antMatchers("/img/*").permitAll()
            //.anyRequest().authenticated()
            //.anyRequest().hasRole("USER")
            
            .antMatchers("/signup*").permitAll()
            .antMatchers("/hotel*").hasAnyAuthority("manager", "admin")
            .antMatchers("/admin/*").hasAuthority("admin")
            .antMatchers("/client/*").hasAuthority("client")
            .anyRequest().authenticated()
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
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.userDetailsService(userService)
				.passwordEncoder(encoder);
	}

}
