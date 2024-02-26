package com.example.competitionmanagment.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLogin {

    private int id;
    private String email;
    private String password;
    private String Token;
    private String ExpirationTime;
    private String RefreshToken;
    private String role;


}