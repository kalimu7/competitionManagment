package com.example.competitionmanagment.Mapper;

import com.example.competitionmanagment.dto.hunting.HuntingDtoResponse;
import com.example.competitionmanagment.entity.Hunting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HuntingResponseMapper {

    HuntingResponseMapper HRM = Mappers.getMapper(HuntingResponseMapper.class);

    @Mapping(target = "fishScore",source = "fish.level.points")
    @Mapping(target = "fishname",source = "fish.name")
    @Mapping(target = "competitioncode",source = "competition.code")
    @Mapping(target = "membernum",source = "member.num")
    HuntingDtoResponse toDto(Hunting hunting);
    Hunting toEntity(HuntingDtoResponse huntingDtoResponse);
}
