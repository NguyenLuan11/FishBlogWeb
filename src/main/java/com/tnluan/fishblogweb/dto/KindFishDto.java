package com.tnluan.fishblogweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KindFishDto {

    private Long id;

    private String kindFishName;

    private String imageUrl;

    private String description;

    private Date createdDate;

    private Date modifiedDate;

    // 1 - N with FishBlog
    private List<FishBlogDto> fishBlogs;
}
