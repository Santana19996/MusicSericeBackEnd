package com.example.musicplayerbackend.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
public class AuthRequest {
    @NotNull
    @Email
    @Length(min = 5)
    private String email;

    @NotNull
    @Length(min = 5)
    private String password;


}
