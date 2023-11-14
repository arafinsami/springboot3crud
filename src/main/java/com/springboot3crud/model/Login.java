package com.springboot3crud.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class Login {

    @NotNull
    @NonNull
    @NotBlank
    private String username;

    @NotNull
    @NonNull
    @NotBlank
    private String password;
}