package com.pinterestclonebackend.demo.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginDTO {

    @NotEmpty
    @Size(min = 1, max = 255)
    @Email
    private String email;

    @NotEmpty
    private String password;
}
