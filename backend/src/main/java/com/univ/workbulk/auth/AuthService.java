package com.univ.workbulk.auth;

import com.univ.workbulk.auth.dto.AuthUserDto;
import com.univ.workbulk.auth.dto.UserLoginDto;
import com.univ.workbulk.auth.dto.UserRegisterDto;
import com.univ.workbulk.exception.AccessException;
import com.univ.workbulk.exception.DuplicateEntryException;
import com.univ.workbulk.user.User;
import com.univ.workbulk.user.UserMapper;
import com.univ.workbulk.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authManager;

    private final TokenService tokenService;

    @Autowired
    public AuthService(UserService userService,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authManager,
                       TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    public AuthUserDto register(UserRegisterDto userDto) {
        userService.getOptionalUserByEmail(userDto.getEmail()).ifPresent(entry -> {
            throw new DuplicateEntryException(
                    String.format("User with email %s already exists", userDto.getEmail()));
        });
        User user = UserMapper.MAPPER.userRegisterDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.save(user);

        var loginDTO = new UserLoginDto(userDto.getEmail(), userDto.getPassword());
        return login(loginDTO);
    }

    public AuthUserDto login(UserLoginDto loginDto) {
        final Authentication auth;
        try {
            auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new AccessException("Wrong email or password");
        }

        var currentUser = (AuthUser) auth.getPrincipal();
        final var user = UserMapper.MAPPER.userToUserDto(
                userService.getUserById(currentUser.getId())
        );
        final String jwt = tokenService.generateToken(currentUser);
        return new AuthUserDto(jwt, user);
    }

}
