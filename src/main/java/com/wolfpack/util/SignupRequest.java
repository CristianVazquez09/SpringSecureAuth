package com.wolfpack.util;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotNull
    @Size(max = 50)
    @Email
    private String username;

    @NotNull
    @Size(min = 3)
    private String password;


}
