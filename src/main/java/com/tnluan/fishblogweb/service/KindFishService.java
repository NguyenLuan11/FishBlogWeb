package com.tnluan.fishblogweb.service;

import com.tnluan.fishblogweb.dto.KindFishDto;

import java.util.List;

public interface KindFishService {

    KindFishDto createKindFish(KindFishDto kindFishDto);

    KindFishDto updateKindFishById(Long id, KindFishDto kindFishDto);

    KindFishDto getKindFishById(Long id);

    List<KindFishDto> getAllKindFish();

    void deleteKindFishById(Long id);
}
