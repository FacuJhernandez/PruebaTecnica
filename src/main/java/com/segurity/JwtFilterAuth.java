package com.segurity;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.config.JwtAuthentication;
import com.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilterAuth extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, io.jsonwebtoken.io.IOException, IOException{

        String header = request.getHeader("Authorization");

        if(header!= null && header.startsWith("Bearer ")){
            String token= header.substring(7);

        

        try{
            String username = jwtUtil.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(token, userDetails)){
                JwtAuthentication authentication = new JwtAuthentication(jwtUtil.extractAllClaims(token));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch(ExpiredJwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            ErrorDTO errorDto= ErrorDTO.builder()
                    .code(HttpServletResponse.SC_UNAUTHORIZED)
                    .message("Token expirado, inicie nuevamente")
                    .build();

            response.getWriter().write(new ObjectMapper().writeValueAsString(errorDto));
            return;
        }
        }
        filterChain.doFilter(request, response);
    }
    
    
}
