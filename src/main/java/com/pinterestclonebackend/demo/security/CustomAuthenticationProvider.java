package com.pinterestclonebackend.demo.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.pinterestclonebackend.demo.constants.Enum.Role;
import com.pinterestclonebackend.demo.entity.User;
import com.pinterestclonebackend.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider   {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new BadCredentialsException("No user registered with this details!"));

        if(bCryptPasswordEncoder.matches(password, user.getUserPassword())) {
            return new CustomToken(email, password, this.getGrantedAuthorities(user.getUserRole()), user.getUserId());
        } else {
            throw new BadCredentialsException("Invalid Password!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getEnumName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
