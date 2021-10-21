package com.univ.workbulk.user;

import com.univ.workbulk.auth.TokenService;
import com.univ.workbulk.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService,
                          TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/me")
    public UserDto getUser(@RequestHeader("Authorization") String token) {
        UUID userId = UUID.fromString(tokenService.extractUserid(token.substring(7)));
        return UserMapper.MAPPER.userToUserDto(userService.getUserById(userId));
    }

    @PostMapping("/{id}/image")
    public String postAvatar(@PathVariable UUID id, @RequestParam("image") MultipartFile file) {
        return userService.uploadAvatar(id, file);
    }

}
