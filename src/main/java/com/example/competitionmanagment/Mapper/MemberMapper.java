package com.example.competitionmanagment.Mapper;

import com.example.competitionmanagment.dto.member.MemberDto;
import com.example.competitionmanagment.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper

public interface MemberMapper {

    MemberMapper mm = Mappers.getMapper(MemberMapper.class);

    @Mapping(target ="num" ,source ="num")
    MemberDto toDto(Member member);
    Member toEntity(MemberDto memberDto);

}
