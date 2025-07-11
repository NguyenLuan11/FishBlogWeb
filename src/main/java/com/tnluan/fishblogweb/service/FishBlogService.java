package com.tnluan.fishblogweb.service;

import com.tnluan.fishblogweb.dto.FishBlogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FishBlogService {

    FishBlogDto createFishBlog(FishBlogDto fishBlogDto);

    FishBlogDto updateFishBlogById(Long id, FishBlogDto fishBlogDto);

    FishBlogDto getFishBlogById(Long id);

    void deleteFishBlogById(Long id);

    List<FishBlogDto> getAllFishBlog();

    Page<FishBlogDto> getAllFishBlogPage(Pageable pageable);

    Page<FishBlogDto> getAllFishBlogByKindFishId(Long kindFishId, Pageable pageable);

    List<FishBlogDto> getAllFishBlogByKindFishId(Long kindFishId);

    Page<FishBlogDto> searchFishBlogByFishName(String fishName, Pageable pageable);
}
