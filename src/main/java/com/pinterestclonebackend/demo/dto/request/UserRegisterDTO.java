package com.pinterestclonebackend.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    @Schema(example = "abc@gmail.com")
    @Email
    @NotBlank
    @Size(min = 1, max = 255)
    private String email;

    @Size(min = 1, max = 50)
    @NotEmpty
    @NotBlank

    private String lastName;

    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 50)
    private String firstName;

    @Size(min = 8, max = 32)
    private String password;

    @Schema(description = "1: Instructor, 2: Student ")
    @NotNull
    private Long roles;
}
