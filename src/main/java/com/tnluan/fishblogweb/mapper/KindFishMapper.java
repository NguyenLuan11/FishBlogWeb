package com.tnluan.fishblogweb.mapper;

import com.tnluan.fishblogweb.dto.FishBlogDto;
import com.tnluan.fishblogweb.dto.KindFishDto;
import com.tnluan.fishblogweb.entity.KindFish;

import java.util.List;
import java.util.stream.Collectors;

public class KindFishMapper {

    public static KindFishDto mapToKindFishDto(KindFish kindFish) {
        List<FishBlogDto> fishBlogDtoList = null;
        if (kindFish.getFishBlogs() != null) {
            fishBlogDtoList = FishBlogMapper.mapFishBlogListToDtoList(kindFish.getFishBlogs());
        }

        return new KindFishDto(
                kindFish.getId(),
                kindFish.getKindFishName(),
                kindFish.getImageUrl(),
                kindFish.getDescription(),
                kindFish.getCreatedDate(),
                kindFish.getModifiedDate(),
                fishBlogDtoList
        );
    }

    public static KindFish mapToKindFishEntity(KindFishDto kindFishDto) {
        return new KindFish(
                kindFishDto.getKindFishName(),
                kindFishDto.getImageUrl(),
                kindFishDto.getDescription()
        );
    }

    // Convert list from Entity -> DTO
    public static List<KindFishDto> mapKindFishListToDtoList(List<KindFish> kindFishList) {
        return kindFishList.stream()
                .map(KindFishMapper::mapToKindFishDto)
                .collect(Collectors.toList());
    }
}
