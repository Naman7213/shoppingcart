//package net.javaguides.springboot.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import net.javaguides.springboot.service.UserService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private UserService userService;
//
//	@Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//	@Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }
//
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers(
//				"/",
//				 "/registration**",
//	                "/js/**",
//	                "/css/**",
//	                "/img/**").permitAll()
//				.antMatchers("/admin/**").hasRole("ADMIN")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.permitAll()
//		.and()
//		.logout()
//		.invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/login?logout")
//		.permitAll();
//	}
//
//}


package net.javaguides.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.javaguides.springboot.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/","/order-history","/admin/addCategoryForm","/admin/addCategory","/admin/addProductForm","/admin/login-admin-page","/admin/loginAsAdmin","/admin/logout","/admin/addProduct", "/filter","/registration**", "/js/**", "/css/**", "/img/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN") // Restrict /admin/** to users with role ADMIN
				.antMatchers("/user/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll();
	}
}
