package com.example.competitionmanagment.Mapper;


import com.example.competitionmanagment.dto.competition.Competitiondto;
import com.example.competitionmanagment.entity.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompetitionMapper {

    CompetitionMapper competitionmapper = Mappers.getMapper(CompetitionMapper.class);

    Competitiondto toDto(Competition competition);

    Competition toEntity(Competitiondto competitiondto);

}
