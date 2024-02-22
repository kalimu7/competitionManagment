package com.example.competitionmanagment.Mapper;

import com.example.competitionmanagment.dto.hunting.HuntingDto;
import com.example.competitionmanagment.entity.Hunting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HuntingMapper {


    HuntingMapper HM = Mappers.getMapper(HuntingMapper.class);


    @Mapping(target = "id",source = "id")
    @Mapping(target = "membernum",source = "user.num")
    @Mapping(target = "competitioncode",source = "competition.code")
    @Mapping(target = "fishname",source = "fish.name")
    HuntingDto toDto(Hunting hunting);

    @Mapping(target = "user.num",source = "membernum")
    @Mapping(target = "competition.code",source = "competitioncode")
    @Mapping(target = "fish.name",source = "fishname")
    Hunting toEntity(HuntingDto huntingDto);

}
