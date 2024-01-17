package com.pinterestclonebackend.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.pinterestclonebackend.demo.entity.User;
import com.pinterestclonebackend.demo.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private  final JwtGeneratorService jwtGeneratorService ;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtGeneratorService.getToken(request) ;

        if (token!=null && jwtGeneratorService.validateToken(token))
        {
            String email = jwtGeneratorService.extractUsername(token);

            Optional<User> user = userRepository.findByUserEmail(email);

            if (user.isPresent()) {
                CustomToken authentication =
                        new CustomToken(user.get().getUsername() ,null , user.get().getAuthorities(), user.get().getUserId());
                log.info("authenticated user with email :{}", email);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }

}