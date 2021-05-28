package com.univ.workbulk.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.UUID;

public class AuthUser extends User {

    @Getter
    @Setter
    private UUID id;

    public AuthUser(UUID id, String email, String password) {
        super(email, password, Collections.emptyList());
        this.id = id;
    }

    public AuthUser(UUID id, String username) {
        super(username, null, Collections.emptyList());
        this.id = id;
    }

}
