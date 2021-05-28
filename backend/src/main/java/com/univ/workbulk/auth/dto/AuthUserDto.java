package com.univ.workbulk.auth.dto;

import com.univ.workbulk.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserDto {

    private String token;

    private UserDto user;

}
