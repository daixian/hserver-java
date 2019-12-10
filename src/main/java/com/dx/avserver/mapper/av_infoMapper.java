package com.dx.avserver.mapper;

import com.dx.avserver.dto.av_infoDto;
import com.dx.avserver.entity.av_info;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

//entity -> DTO
@Mapper(componentModel = "spring")
public interface av_infoMapper {

    public static av_infoMapper INSTANCE = Mappers.getMapper(av_infoMapper.class);

//    @Mappings({
//            @Mapping(source = "type.name",target = "typeName"),
//            @Mapping(source = "good.id",target = "goodId"),
//            @Mapping(source = "good.title",target = "goodName"),
//            @Mapping(source = "good.price",target = "goodPrice")
//    })
        // @InheritInverseConfiguration
    av_infoDto toDto(av_info type);


//    @Mapping(target = "ID", ignore = true)
    void toTarget(av_info source, @MappingTarget av_info target);

}