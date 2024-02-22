package com.example.competitionmanagment.Mapper;

import com.example.competitionmanagment.dto.member.MemberDtoResponse;
import com.example.competitionmanagment.dto.member.MemberDtoWinnerResponse;
import com.example.competitionmanagment.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberDtoResponseMapper {

    MemberDtoResponseMapper MDRM = Mappers.getMapper(MemberDtoResponseMapper.class);
    MemberDtoWinnerResponse toDto(User user);
    User toEntity(MemberDtoResponse memberDtoResponse);
}
