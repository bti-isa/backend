package com.isa.BloodTransferInstitute.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;
	private final UserAuthenticationProvider userAuthenticationProvider;
	private final RequestMatcher AUTH_MATCHER = new OrRequestMatcher(
		new AntPathRequestMatcher( API_URL + "/User/authenticate"),
		new AntPathRequestMatcher(API_URL + "/Patient/", "POST"),
		new AntPathRequestMatcher(API_URL + "/Patient/checkUnique"),
		new AntPathRequestMatcher(API_URL + "/Patient/activate/{id}"),
		new AntPathRequestMatcher(API_URL + "/BloodBank/search"),
		new AntPathRequestMatcher(API_URL + "/map"),
		new AntPathRequestMatcher(API_URL + "/socket")
	);
//	private final RequestMatcher COMMON_MATCHER = new OrRequestMatcher(
//		new AntPathRequestMatcher(API_URL + "/BloodBank/search"),
//		new AntPathRequestMatcher(API_URL + "/BloodBank/all"),
//		new AntPathRequestMatcher(API_URL + "/BloodBank", "GET"),
//		new AntPathRequestMatcher(API_URL + "/BloodBank/simple")
//	);
//	private final RequestMatcher PATIENT_MATCHER = new OrRequestMatcher(
//		new AntPathRequestMatcher(API_URL + "/Appointment/schedule"),
//		new AntPathRequestMatcher(API_URL + "/Appointment/datetime", "GET"),
//		new AntPathRequestMatcher(API_URL + "/Appointment/available/{number}/{size}/{direction}", "GET"),
//		new AntPathRequestMatcher(API_URL + "/polls/add"),
//		new AntPathRequestMatcher(API_URL + "/Patient/", "PATCH")
//	);
//	private final RequestMatcher SYSTEM_ADMIN_MATCHER = new OrRequestMatcher(
//		new AntPathRequestMatcher(API_URL + "/Admin/add"),
//		new AntPathRequestMatcher(API_URL + "/Admin/update"),
//		new AntPathRequestMatcher(API_URL + "/BloodBank/add"),
//		new AntPathRequestMatcher(API_URL + "/BloodBank/update"),
//		new AntPathRequestMatcher(API_URL + "/Patient/{id}", "DELETE"),
//		new AntPathRequestMatcher(API_URL + "/Patient/search"),
//		new AntPathRequestMatcher(API_URL + "/Patient/all"),
//		new AntPathRequestMatcher(API_URL + "/Patient/{id}", "GET")
//	);
//	private final RequestMatcher INSTITUTE_ADMIN_MATCHER = new OrRequestMatcher(
//		new AntPathRequestMatcher(API_URL + "/Appointment/", "POST"),
//		new AntPathRequestMatcher(API_URL + "/Appointment/finish"),
//		new AntPathRequestMatcher(API_URL + "/Appointment/all"),
//		new AntPathRequestMatcher(API_URL + "/Appointment/{id}"),
//		new AntPathRequestMatcher(API_URL + "/Patient/all"),
//		new AntPathRequestMatcher(API_URL + "/Patient/{id}", "GET")
//	);

	private static final String API_URL = "/api";

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers(AUTH_MATCHER).permitAll()
//			.requestMatchers(COMMON_MATCHER).hasAnyAuthority("SYSTEM_ADMIN", "PATIENT", "INSTITUTE_ADMIN")
//			.requestMatchers(PATIENT_MATCHER).hasAuthority("PATIENT")
//			.requestMatchers(INSTITUTE_ADMIN_MATCHER).hasAuthority("INSTITUTE_ADMIN")
//			.requestMatchers(SYSTEM_ADMIN_MATCHER).hasAuthority("SYSTEM_ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.cors()
			.and()
			.authenticationProvider(userAuthenticationProvider)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.httpBasic(withDefaults())
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
