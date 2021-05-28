package com.univ.workbulk.user;

import com.univ.workbulk.auth.dto.UserRegisterDto;
import com.univ.workbulk.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workgroups", ignore = true)
    @Mapping(target = "password", ignore = true)
    User userRegisterDtoToUser(UserRegisterDto userRegisterDto);

}
