package com.rizalfadiaalfikri.echosphere.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.repository.UserRepository;
import com.rizalfadiaalfikri.echosphere.utils.exceptions.RowNotFoundDetailNullException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(username)
                .orElseThrow(() -> new RowNotFoundDetailNullException("User with email " + username + " is not found"));

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        return new User(users.getEmail(), users.getPassword(), authorities);
    }

}
