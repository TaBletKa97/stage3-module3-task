package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "author.createDate", target = "authorCreateTime")
    @Mapping(source = "author.lastUpdateDate", target = "authorLastUpdateTime")
    NewsDTOResponse mapNews(News newsModel);

    @Mapping(source = "authorId", target = "author.id")
    @Mapping(target = "tags", ignore = true)
    News unmapNewsReq(NewsDTORequest request);

    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "authorName", target = "author.name")
    @Mapping(source = "authorCreateTime", target = "author.createDate")
    @Mapping(source = "authorLastUpdateTime", target = "author.lastUpdateDate")
    News unmapNewsResp(NewsDTOResponse resp);
}
