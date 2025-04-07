package com.tnluan.fishblogweb.mapper;

import com.tnluan.fishblogweb.dto.FishBlogDto;
import com.tnluan.fishblogweb.entity.FishBlog;
import com.tnluan.fishblogweb.entity.KindFish;

import java.util.List;
import java.util.stream.Collectors;

public class FishBlogMapper {

    public static FishBlogDto mapFishBlogToDto(FishBlog fishBlog) {
        return new FishBlogDto(
                fishBlog.getId(),
                fishBlog.getKindFish().getId(),
                fishBlog.getFishName(),
                fishBlog.getThumbnailUrl(),
                fishBlog.getContentBlog(),
                fishBlog.getCreatedDate(),
                fishBlog.getModifiedDate()
        );
    }

    public static FishBlog mapFishBlogToEntity(FishBlogDto fishBlogDto, KindFish kindFish) {
        return new FishBlog(
                kindFish,
                fishBlogDto.getFishName(),
                fishBlogDto.getThumbnailUrl(),
                fishBlogDto.getContentBlog()
        );
    }

    public static List<FishBlogDto> mapFishBlogListToDtoList(List<FishBlog> fishBlogList) {
        return fishBlogList.stream()
                .map(FishBlogMapper::mapFishBlogToDto)
                .collect(Collectors.toList());
    }
}
