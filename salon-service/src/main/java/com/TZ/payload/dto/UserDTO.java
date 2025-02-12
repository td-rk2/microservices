package com.TZ.payload.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDTO {
    private Long Id;
    private String fullName;
    private String email;
}
