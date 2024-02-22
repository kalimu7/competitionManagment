package com.example.competitionmanagment.Mapper;

import com.example.competitionmanagment.dto.ranking.RankingDto;
import com.example.competitionmanagment.entity.Ranking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RankingMapper {

    RankingMapper RM = Mappers.getMapper(RankingMapper.class);

    @Mapping(target = "competitoncode",source = "id.competitoncode")
    @Mapping(target = "membernum",source = "id.membernum")
    RankingDto toDto(Ranking ranking);
    @Mapping(target = "id.competitoncode",source = "competitoncode")
    @Mapping(target = "id.membernum",source = "membernum")
    Ranking toEntity(RankingDto rankingDto);


}
