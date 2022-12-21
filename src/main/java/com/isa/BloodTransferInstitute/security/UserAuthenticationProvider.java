package com.isa.BloodTransferInstitute.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

//@Component
//@RequiredArgsConstructor
//public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
//
//	private final UserDetailsService userDetailsService;
//	private final PasswordEncoder encoder;
//
//	@Override
//	protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication)
//		throws AuthenticationException {
//		if(authentication.getCredentials() == null || userDetails.getPassword() == null) {
//			throw new BadCredentialsException("Credentials may not be null");
//		}
//		if(!encoder.matches((String) authentication.getCredentials(), userDetails.getPassword())) {
//			throw new BadCredentialsException("Invalid credentials");
//		}
//	}
//
//	@Override
//	protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//		return userDetailsService.loadUserByUsername(username);
//	}
//}
