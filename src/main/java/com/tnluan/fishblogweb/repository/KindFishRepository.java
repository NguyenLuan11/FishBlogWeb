package com.tnluan.fishblogweb.repository;

import com.tnluan.fishblogweb.entity.KindFish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KindFishRepository extends JpaRepository<KindFish, Long> {

    Optional<KindFish> findByKindFishName(String kindFishName);
}
