package com.dx.avserver.mapper;

import com.dx.avserver.dto.AVInfoDto;
import com.dx.avserver.entity.AVInfo;
import com.dx.avserver.entity.embeddable.AVGalleryEmb;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

//entity -> DTO
@Mapper(componentModel = "spring")
public abstract class AVInfoMapper {

    public static AVInfoMapper INSTANCE = Mappers.getMapper(AVInfoMapper.class);

    //@InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "info.avid", target = "av_id"),
            @Mapping(source = "info.javBooksId", target = "javbooks_id")
    })
    public abstract AVInfoDto toDto(AVInfo info);

    public abstract List<AVInfoDto> toDto(List<AVInfo> infos);

//--------------------------------------------------------------------------------------------------

    /**
     * 一个自定义的转换方法
     * 出处: https://github.com/mapstruct/mapstruct/issues/584
     *
     * @param listGalleryEmb 这是AVInfo的gallery成员类型
     * @return 这里要转换成的AVInfoDto里面的gallery类型
     */
    public List<String> AVGalleryEmbToString(List<AVGalleryEmb> listGalleryEmb) {
        List<String> list = new ArrayList<>();
        for (AVGalleryEmb item : listGalleryEmb) {
            list.add(item.getImageUrl());
        }
        return list;
    }
}