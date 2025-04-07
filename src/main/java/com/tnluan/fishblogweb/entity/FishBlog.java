package com.tnluan.fishblogweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "fish_blog")
public class FishBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kind_fish_id", nullable = false)
    private KindFish kindFish;

    @Column(name = "fish_name", nullable = false)
    private String fishName;

    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;

    @Column(name = "content_blog", columnDefinition = "LONGTEXT", nullable = false)
    @Lob
    private String contentBlog;

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;

    public FishBlog(KindFish kindFish, String fishName, String thumbnailUrl, String contentBlog) {
        this.kindFish = kindFish;
        this.fishName = fishName;
        this.thumbnailUrl = thumbnailUrl;
        this.contentBlog = contentBlog;
    }
}
