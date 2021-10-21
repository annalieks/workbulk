package com.univ.workbulk.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String avatar;

}
