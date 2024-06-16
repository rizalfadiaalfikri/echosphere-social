package com.rizalfadiaalfikri.echosphere.services.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rizalfadiaalfikri.echosphere.config.JwtUtils;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.models.req.LoginDto;
import com.rizalfadiaalfikri.echosphere.models.res.LoginResponse;
import com.rizalfadiaalfikri.echosphere.repository.UserRepository;
import com.rizalfadiaalfikri.echosphere.services.AuthService;
import com.rizalfadiaalfikri.echosphere.utils.exceptions.BadRequestException;
import com.rizalfadiaalfikri.echosphere.utils.exceptions.RowNotFoundException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public LoginResponse login(LoginDto dto) {
        Users users = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new RowNotFoundException("Users with email " + dto.getEmail() + " is not found"));

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            return new LoginResponse(users.getEmail(), jwt);
        } catch (AuthenticationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }

    }

}
