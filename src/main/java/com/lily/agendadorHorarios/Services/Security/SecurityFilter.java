package com.lily.agendadorHorarios.Services.Security;

import com.lily.agendadorHorarios.Infrastructure.Entity.User.UserEntity;
import com.lily.agendadorHorarios.Infrastructure.Repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    @Lazy
    private SecurityContextRepository securityContextRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // System.out.println("REQUISIÇÃO: " + request.getRequestURI());
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        // System.out.println("TOKEN RECEBIDO: " + token + " LOGIN: " + login);

        if (login != null) {
            UserEntity user = userRepository.findByEmail(login)
                    .orElseThrow(() -> new RuntimeException("User Not Found"));
            var roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, roles);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            var context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context, request, response);
        }

        filterChain.doFilter(request, response);
        // System.out.println("AUTHENTICATION APÓS FILTER: " + SecurityContextHolder.getContext().getAuthentication());
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}