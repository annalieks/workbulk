package com.univ.workbulk.auth;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 864_000_000; // 1 day

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    protected static final String[] ROUTES_WHITE_LIST = {"/auth/signin", "/auth/signup"};

    private SecurityConstants() {
    }

}