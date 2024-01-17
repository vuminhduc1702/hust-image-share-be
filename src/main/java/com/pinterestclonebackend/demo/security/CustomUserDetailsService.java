package com.pinterestclonebackend.demo.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.pinterestclonebackend.demo.constants.ApiResponseCode;
import com.pinterestclonebackend.demo.entity.User;
import com.pinterestclonebackend.demo.exception.BusinessException;
import com.pinterestclonebackend.demo.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new BusinessException(ApiResponseCode.USER_NOT_FOUND));
        return user;
    }
}
