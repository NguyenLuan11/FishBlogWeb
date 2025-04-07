package com.tnluan.fishblogweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "kind_fish")
public class KindFish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kind_fish_name", nullable = false)
    private String kindFishName;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = false)
    @Lob
    private String description;

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;

    // 1 - N with FishBlog
    // cascade = CascadeType.ALL: When a KindFish is deleted, the related FishBlog entries will also be deleted.
    // orphanRemoval = true: If a FishBlog no longer belongs to any KindFish, it will be automatically deleted.
    @OneToMany(mappedBy = "kindFish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FishBlog> fishBlogs;

    public KindFish(String kindFishName, String imageUrl, String description) {
        this.kindFishName = kindFishName;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
