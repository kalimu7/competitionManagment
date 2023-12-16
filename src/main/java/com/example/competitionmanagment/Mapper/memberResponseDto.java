package com.example.competitionmanagment.Mapper;

import com.example.competitionmanagment.dto.member.MemberDtoResponse;
import com.example.competitionmanagment.entity.Ranking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface memberResponseDto {

    memberResponseDto MRD = Mappers.getMapper(memberResponseDto.class);
    //@Mapping(target = "",source = "")
    MemberDtoResponse toDto(Ranking ranking);


}
