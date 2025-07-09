package com.tnluan.fishblogweb.service.impl;

import com.tnluan.fishblogweb.dto.FishBlogDto;
import com.tnluan.fishblogweb.entity.FishBlog;
import com.tnluan.fishblogweb.entity.KindFish;
import com.tnluan.fishblogweb.exception.ResourceNotFoundException;
import com.tnluan.fishblogweb.exception.ResourceUnprocessableEntityException;
import com.tnluan.fishblogweb.mapper.FishBlogMapper;
import com.tnluan.fishblogweb.repository.FishBlogRepository;
import com.tnluan.fishblogweb.repository.KindFishRepository;
import com.tnluan.fishblogweb.service.FishBlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FishBlogServiceImpl implements FishBlogService {

    private FishBlogRepository fishBlogRepository;

    private KindFishRepository kindFishRepository;

    private FishBlog findFishBlogById(Long id) {
        return fishBlogRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Fish's blog isn't exists with given id: " + id)
        );
    }

    private KindFish findKindFishById(Long id) {
        return kindFishRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Kind of fish isn't exists with given id: " + id)
        );
    }

    private void checkValidate(Long kindFishId, String fishName, String thumbnailUrl, String contentBlog) {
        if (kindFishId == null) {
            throw new ResourceUnprocessableEntityException("Kind's ID is required! Please provide a valid KindFish ID!");
        }
        if (fishName == null || fishName.trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Fish name is required! Please provide a valid fishName!");
        }
        if (thumbnailUrl == null || thumbnailUrl.trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Thumbnail's url is required! Please provide a valid thumbnail's url!");
        }
        if (contentBlog == null || contentBlog.trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Blog's content is required! Please provide a valid blog's content!");
        }
    }

    @Override
    public FishBlogDto createFishBlog(FishBlogDto fishBlogDto) {
        checkValidate(fishBlogDto.getKindFishId(), fishBlogDto.getFishName(),
                fishBlogDto.getThumbnailUrl(), fishBlogDto.getContentBlog());

        KindFish kindFish = findKindFishById(fishBlogDto.getKindFishId());
        FishBlog fishBlog = FishBlogMapper.mapFishBlogToEntity(fishBlogDto, kindFish);

        FishBlog saveFishBlog = fishBlogRepository.save(fishBlog);
        return FishBlogMapper.mapFishBlogToDto(saveFishBlog);
    }

    @Override
    public FishBlogDto updateFishBlogById(Long id, FishBlogDto fishBlogDto) {
        checkValidate(fishBlogDto.getKindFishId(), fishBlogDto.getFishName(),
                fishBlogDto.getThumbnailUrl(), fishBlogDto.getContentBlog());

        FishBlog existingFishBlog = findFishBlogById(id);
        KindFish kindFish = findKindFishById(fishBlogDto.getKindFishId());

        existingFishBlog.setKindFish(kindFish);
        existingFishBlog.setFishName(fishBlogDto.getFishName());
        existingFishBlog.setThumbnailUrl(fishBlogDto.getThumbnailUrl());
        existingFishBlog.setContentBlog(fishBlogDto.getContentBlog());

        FishBlog updatedFishBlog = fishBlogRepository.save(existingFishBlog);
        return FishBlogMapper.mapFishBlogToDto(updatedFishBlog);
    }

    @Override
    public FishBlogDto getFishBlogById(Long id) {
        return FishBlogMapper.mapFishBlogToDto(findFishBlogById(id));
    }

    @Override
    public List<FishBlogDto> getAllFishBlog() {
        List<FishBlog> fishBlogList = fishBlogRepository.findAll();
        return FishBlogMapper.mapFishBlogListToDtoList(fishBlogList);
    }

    @Override
    public Page<FishBlogDto> getAllFishBlogPage(Pageable pageable) {
        return fishBlogRepository.findAll(pageable)
                .map(FishBlogMapper::mapFishBlogToDto);
    }

    @Override
    public Page<FishBlogDto> getAllFishBlogByKindFishId(Long kindFishId, Pageable pageable) {
        return fishBlogRepository.findByKindFishId(kindFishId, pageable)
                .map(FishBlogMapper::mapFishBlogToDto);
    }

    @Override
    public Page<FishBlogDto> searchFishBlogByFishName(String fishName, Pageable pageable) {
        if (fishName == null || fishName.trim().isEmpty()) {
            return getAllFishBlogPage(pageable);
        }
        return fishBlogRepository.findByFishNameContainingIgnoreCase(fishName, pageable)
                .map(FishBlogMapper::mapFishBlogToDto);
    }

    @Override
    public void deleteFishBlogById(Long id) {
        if (!fishBlogRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fish's blog isn't exists with given id: " + id);
        }
        fishBlogRepository.deleteById(id);
    }
}
