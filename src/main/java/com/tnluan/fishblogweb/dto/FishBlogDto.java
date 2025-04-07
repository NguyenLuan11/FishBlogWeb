package com.tnluan.fishblogweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FishBlogDto {

    private Long id;

    private Long kindFishId;

    private String fishName;

    private String thumbnailUrl;

    private String contentBlog;

    private Date createdDate;

    private Date modifiedDate;
}
