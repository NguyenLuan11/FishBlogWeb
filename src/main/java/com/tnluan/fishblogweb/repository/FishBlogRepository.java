package com.tnluan.fishblogweb.repository;

import com.tnluan.fishblogweb.entity.FishBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FishBlogRepository extends JpaRepository<FishBlog, Long> {

    // Find list of blog by KindFish's ID and pageable
    // SELECT * FROM fish_blog WHERE kind_fish_id = ? LIMIT ?, ?
    Page<FishBlog> findByKindFishId(Long kindFishId, Pageable pageable);

    List<FishBlog> findByKindFishId(Long kindFishId);

    // Find list of blog have name contain a string (ignore case)
    // SELECT * FROM fish_blog WHERE LOWER(fish_name) LIKE LOWER('%keyword%') LIMIT ?, ?
    Page<FishBlog> findByFishNameContainingIgnoreCase(String fishName, Pageable pageable);
}
