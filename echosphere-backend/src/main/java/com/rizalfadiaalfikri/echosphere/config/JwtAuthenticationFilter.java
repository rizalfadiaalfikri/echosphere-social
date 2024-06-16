package com.rizalfadiaalfikri.echosphere.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizalfadiaalfikri.echosphere.services.CustomUserDetailsService;
import com.rizalfadiaalfikri.echosphere.utils.responses.ErrorResponse;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${application.version}")
    String applicationVersion;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (jwt != null && !jwtUtils.validateJwtToken(jwt)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                ErrorResponse errorResponse = new ErrorResponse(401, "Invalid Token", applicationVersion);

                String jsonResponse = objectMapper.writeValueAsString(errorResponse);
                // Set the content type to application/json
                response.setContentType("application/json");

                // Write the JSON response to the output stream
                response.getWriter().write(jsonResponse);
                response.getWriter().flush();

                return;
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // Create a JSON response object
            ErrorResponse errorResponse = new ErrorResponse(401, "Unauthorized", applicationVersion);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(errorResponse);
            // Set the content type to application/json
            response.setContentType("application/json");

            // Write the JSON response to the output stream
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();

            return;
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
