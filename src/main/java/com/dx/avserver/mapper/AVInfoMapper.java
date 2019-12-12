package com.dx.avserver.mapper;

import com.dx.avserver.dto.AVInfoDto;
import com.dx.avserver.entity.AVGallery;
import com.dx.avserver.entity.AVInfo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

//entity -> DTO
@Mapper(componentModel = "spring")
public interface AVInfoMapper {

    AVInfoMapper INSTANCE = Mappers.getMapper(AVInfoMapper.class);

    //@InheritInverseConfiguration
    @Mappings({@Mapping(source = "info.avid", target = "av_id")})
    AVInfoDto toDto(AVInfo info);

    List<AVInfoDto> toDto(List<AVInfo> infos);

    //@Mapping(target = "ID", ignore = true)
    void toTarget(AVInfo source, @MappingTarget AVInfo target);
}