package com.example.singers_listing.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    @NotBlank(message = "Enter first name")
    private String firstName;

    @NotBlank(message = "Enter last name")
    private String lastName;

    @NotBlank(message = "gender is required")
    private String gender;

    @NotNull(message = "Enter age")
    @Min(value=12, message="age must be at least 12")
    @Max(value=100, message="age must be at most 100")
    private int age;

    @NotBlank(message = "Enter style")
    private String style;
}