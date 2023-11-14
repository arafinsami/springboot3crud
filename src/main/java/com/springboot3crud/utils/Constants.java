package com.springboot3crud.utils;

public final class Constants {

    public static final String[] PUBLIC_MATECHERS = {
            "/setup",
            "/login",
            "/myget",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/vi",
            "/configuration/security",
            "/swagger-vi/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/context-path/**",
            "swagger-ui/**"
    };

    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "created";
    public static final String RESOURCE_NOT_FOUND = "resource not found";

    public static final String ALREADY_EXIST = "Already exist !!!";

    private Constants() {
    }
}
