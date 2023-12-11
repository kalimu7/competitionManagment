package com.example.competitionmanagment.Mapper;

import com.example.competitionmanagment.dto.fish.FishDto;
import com.example.competitionmanagment.entity.Fish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FishMapper {

    FishMapper FM = Mappers.getMapper(FishMapper.class);


    @Mapping(target = "score",source = "level.points")
    FishDto toDto(Fish fish);
    Fish toEntity(FishDto fishDto);

}
