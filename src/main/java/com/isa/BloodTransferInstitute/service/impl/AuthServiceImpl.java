package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.auth.ChangePasswordDTO;
import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.exception.UserHasNoRoleException;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Optional<Boolean> changePassword(ChangePasswordDTO dto) {
        var user = userRepository.findByUsername(dto.getEmail());
        return Optional.ofNullable(executePasswordChange(user, dto.getPassword()).orElseThrow(NotFoundException::new));
    }

    private Optional<Boolean> executePasswordChange(User user, String newPassword){
        try{
            user.setPassword(newPassword);
            userRepository.save(user);
            return Optional.of(true);
        }catch (Exception e){
            return Optional.of(false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User: " + username + " not found.");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        } else {
            throw new UserHasNoRoleException("User: " + username + " has no role.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), !user.isAccountExpired(), !user.isCredentialsExpired(), !user.isAccountLocked(), authorities);
    }

}
