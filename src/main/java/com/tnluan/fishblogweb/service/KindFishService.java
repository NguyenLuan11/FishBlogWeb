package com.tnluan.fishblogweb.service;

import com.tnluan.fishblogweb.dto.KindFishDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KindFishService {

    KindFishDto createKindFish(KindFishDto kindFishDto);

    KindFishDto updateKindFishById(Long id, KindFishDto kindFishDto);

    KindFishDto getKindFishById(Long id);

    List<KindFishDto> getAllKindFish();

    Page<KindFishDto> getKindFishPage(Pageable pageable);

    void deleteKindFishById(Long id);
}
