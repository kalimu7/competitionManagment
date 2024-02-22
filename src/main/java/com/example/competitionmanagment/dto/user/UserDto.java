package com.example.competitionmanagment.dto.user;

import com.example.competitionmanagment.enums.Roles;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;
    private String familyName;
    private String email;
    private String password;
    private Roles role;

}
