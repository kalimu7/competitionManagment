package com.example.competitionmanagment.Mapper;

import com.example.competitionmanagment.dto.user.UserDto;
import com.example.competitionmanagment.entity.UserInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper UMI = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserInfoEntity userInfoEntity);

    UserInfoEntity toEntity(UserDto userDto);


}
