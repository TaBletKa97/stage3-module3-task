package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
    
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    TagDTOResponse mapTag(Tag tag);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Tag unmapTagReq(TagDTORequest request);
}
