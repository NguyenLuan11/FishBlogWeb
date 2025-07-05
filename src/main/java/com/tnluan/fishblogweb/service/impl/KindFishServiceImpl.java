package com.tnluan.fishblogweb.service.impl;

import com.tnluan.fishblogweb.dto.KindFishDto;
import com.tnluan.fishblogweb.entity.KindFish;
import com.tnluan.fishblogweb.exception.ResourceNotFoundException;
import com.tnluan.fishblogweb.exception.ResourceUnprocessableEntityException;
import com.tnluan.fishblogweb.mapper.KindFishMapper;
import com.tnluan.fishblogweb.repository.KindFishRepository;
import com.tnluan.fishblogweb.service.KindFishService;
import com.tnluan.fishblogweb.util.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KindFishServiceImpl implements KindFishService {

    private KindFishRepository kindFishRepository;

    private UploadService uploadService;

    private KindFish findKindFishById(Long id) {
        return kindFishRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Kind of fish isn't exists with given id: " + id)
        );
    }

    private void checkValidate(String kindFishName, String imageUrl, String description) {
        if (kindFishName == null || kindFishName.trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Kind fish name is required! Please provide a valid kindFishName!");
        }
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Image's url is required! Please provide a valid image's url!");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Description is required! Please provide a valid description!");
        }
    }

    @Override
    public KindFishDto createKindFish(KindFishDto kindFishDto) {
        checkValidate(kindFishDto.getKindFishName(), kindFishDto.getImageUrl(), kindFishDto.getDescription());

        KindFish kindFish = KindFishMapper.mapToKindFishEntity(kindFishDto);
        KindFish saveKindFish = kindFishRepository.save(kindFish);
        return KindFishMapper.mapToKindFishDto(saveKindFish);
    }

    @Override
    public KindFishDto updateKindFishById(Long id, KindFishDto kindFishDto) {
        checkValidate(kindFishDto.getKindFishName(), kindFishDto.getImageUrl(), kindFishDto.getDescription());

        KindFish kindFish = findKindFishById(id);

        kindFish.setKindFishName(kindFishDto.getKindFishName());
        kindFish.setImageUrl(kindFishDto.getImageUrl());
        kindFish.setDescription(kindFish.getDescription());

        KindFish updateKindFish = kindFishRepository.save(kindFish);
        return KindFishMapper.mapToKindFishDto(updateKindFish);
    }

    @Override
    public KindFishDto getKindFishById(Long id) {
        return KindFishMapper.mapToKindFishDto(findKindFishById(id));
    }

    @Override
    public List<KindFishDto> getAllKindFish() {
        List<KindFish> kindFishList = kindFishRepository.findAll();
        return KindFishMapper.mapKindFishListToDtoList(kindFishList);
    }

    @Override
    public Page<KindFishDto> getKindFishPage(Pageable pageable) {
        Page<KindFish> page = kindFishRepository.findAll(pageable);
        return page.map(KindFishMapper::mapToKindFishDto);
    }

    @Override
    public void deleteKindFishById(Long id) {
        KindFishDto kindFish = KindFishMapper.mapToKindFishDto(findKindFishById(id));

        // Remove image in dir
        if (kindFish.getImageUrl() != null && !kindFish.getImageUrl().isEmpty()) {
            uploadService.deleteImage(kindFish.getImageUrl());
        }

        // Delete KindFish in DB
        kindFishRepository.deleteById(id);
    }
}
