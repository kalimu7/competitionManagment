package com.example.competitionmanagment.dto.user;

import com.example.competitionmanagment.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "familyname is required")
    private String familyName;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "Passwordis required")
    private String password;

    @NotBlank(message = "role is required")
    private Roles role;

    @NotBlank(message = "identity number is required")
    private String identityNumber;

}
