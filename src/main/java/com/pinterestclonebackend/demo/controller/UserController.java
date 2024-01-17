package com.pinterestclonebackend.demo.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pinterestclonebackend.demo.dto.CommonResponseDTO;
import com.pinterestclonebackend.demo.dto.EmailDTO;
import com.pinterestclonebackend.demo.dto.projection.UserProjection;
import com.pinterestclonebackend.demo.dto.request.LoginDTO;
import com.pinterestclonebackend.demo.dto.request.UserRegisterDTO;
import com.pinterestclonebackend.demo.service.CustomUserService;
import com.pinterestclonebackend.demo.utils.SecurityUtils;

import java.util.Map;

@CrossOrigin
@Tag(name = "A. Authentication", description = "Đăng kí đăng nhập logout")
@RestController
@AllArgsConstructor
@RequestMapping("/api/public")
public class UserController {

    private final CustomUserService userService;

    @Operation(summary = "Đăng kí tài khoản", description = "Đăng kí tài khoản: lưu ý mật khẩu có 8 chữ cái cả số cả chữ và kí tự viết hoa")
    @PostMapping("/register")
    public ResponseEntity<CommonResponseDTO> register (@Valid @RequestBody UserRegisterDTO registerDto)
    {
        return  ResponseEntity.ok(userService.register(registerDto));
    }

    @Operation(summary = "Đăng nhập", description = "Đăng nhập")
    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(@Valid @RequestBody LoginDTO loginDto)
    {
        return  ResponseEntity.ok(Map.of("accessToken", userService.authenticate(loginDto)));
    }

    @Operation(summary = "Quên mật khẩu", description = "Quên mật khẩu")
    @PostMapping("/reset-password")
    public ResponseEntity<CommonResponseDTO> forgot(@Valid @RequestBody EmailDTO emailDTO) {
        return  ResponseEntity.ok(userService.resetPassWord(emailDTO));
    }

    @Operation(summary = "Thông tin người dùng", description = "Thông tin người dùng")
    @GetMapping
    public ResponseEntity<UserProjection> getUserInfo() {
        return ResponseEntity.ok(userService.getUserInfo(SecurityUtils.getCurrentUserIdLogin().get()));
    }
}
