package com.univ.workbulk.auth;

import com.univ.workbulk.auth.dto.AuthUserDto;
import com.univ.workbulk.auth.dto.UserLoginDto;
import com.univ.workbulk.auth.dto.UserRegisterDto;
import com.univ.workbulk.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public AuthUserDto signUp(@RequestBody UserRegisterDto user) {
        return authService.register(user);
    }

    @PostMapping("/signin")
    public AuthUserDto signIn(@RequestBody UserLoginDto user) {
        return authService.login(user);
    }

}
