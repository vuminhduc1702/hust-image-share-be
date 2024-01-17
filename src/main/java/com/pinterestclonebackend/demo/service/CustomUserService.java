package com.pinterestclonebackend.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.pinterestclonebackend.demo.constants.ApiResponseCode;
import com.pinterestclonebackend.demo.constants.Enum.Role;
import com.pinterestclonebackend.demo.dto.CommonResponseDTO;
import com.pinterestclonebackend.demo.dto.EmailDTO;
import com.pinterestclonebackend.demo.dto.projection.UserProjection;
import com.pinterestclonebackend.demo.dto.request.LoginDTO;
import com.pinterestclonebackend.demo.dto.request.UserRegisterDTO;
import com.pinterestclonebackend.demo.entity.User;
import com.pinterestclonebackend.demo.exception.BusinessException;
import com.pinterestclonebackend.demo.repository.UserRepository;
import com.pinterestclonebackend.demo.security.JwtGeneratorService;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtGeneratorService jwtUtilities;

    public CommonResponseDTO register(UserRegisterDTO registerDto) {
        if (userRepository.findByUserEmail(registerDto.getEmail()).isPresent()) {
            throw new BusinessException(ApiResponseCode.EMAIL_IS_ALREADY_TAKEN);
        } else {
            User user = new User();
            user.setUserEmail(registerDto.getEmail());
            user.setUserFirstName(registerDto.getFirstName());
            user.setUserLastName(registerDto.getLastName());
            user.setUserPassword(passwordEncoder.encode(registerDto.getPassword()));
            Role role = Role.USER;
            if(registerDto.getRoles() == 1) {
                role = Role.ADMIN;
            }
            user.setUserRole(role);
            userRepository.save(user);
            return new CommonResponseDTO("200", "Ok");

        }
    }

    public UserProjection getUserInfo(Long userId) {
        return userRepository.findFirstInfoUser(userId).orElseThrow(() -> new BusinessException(ApiResponseCode.USER_NOT_FOUND));
    }

    public String authenticate(LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUserEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.updateLastLogin(user.getUserId());
        List<String> rolesNames = new ArrayList<>();
        rolesNames.add(user.getUserRole().enumName);
        String token = jwtUtilities.generateToken(user.getUsername(), rolesNames, user.getUserId());
        return token;
    }


    public CommonResponseDTO resetPassWord(EmailDTO emailDTO) {
        User user = userRepository.findByUserEmail(emailDTO.getEmail())
                .orElseThrow(() -> new BusinessException(ApiResponseCode.USER_NOT_FOUND));

        String newPassword = this.generateSecurePass();
        user.setUserPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
        return new CommonResponseDTO("200", "Ok");
    }


    private String generateSecurePass() {
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);
        LCR.setNumberOfCharacters(2);

        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);
        UCR.setNumberOfCharacters(2);

        CharacterRule DCR = new CharacterRule(EnglishCharacterData.Digit);
        DCR.setNumberOfCharacters(2);

        PasswordGenerator passGen = new PasswordGenerator();

        String password = passGen.generatePassword(6, UCR, LCR, DCR);

        System.out.println(password);

        return password;
    }

}
