package com.pinterestclonebackend.demo.entity;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import com.pinterestclonebackend.demo.repository.UserRepository;
import com.pinterestclonebackend.demo.utils.SecurityUtils;

import java.util.Optional;


@AllArgsConstructor
public class AuditorAwareImpl implements AuditorAware<Long> {

    private UserRepository userRepository;

    @Override
    public Optional<Long> getCurrentAuditor() {
        return SecurityUtils.getCurrentUserIdLogin();
    }
}
