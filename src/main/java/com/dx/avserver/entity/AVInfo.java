package com.dx.avserver.entity;

import com.dx.avserver.entity.embeddable.AVGalleryEmb;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

/**
 * 对应数据库的实体
 */
@Data
@Entity
@Table(name = "av_info_tbl")
public class AVInfo implements Serializable {

    @Id //entity的
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(name = "av_id", unique = true, nullable = false)
    private String avid;

    @Column(name = "javbooks_id", unique = true)
    private int javBooksId;

    @ElementCollection
    @CollectionTable(name = "av_performers_join_tbl", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "performer")
    private List<String> performers;

    @Column(name = "title")
    private String title;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "releases_time")
    private String releases_time;

    @Column(name = "film_time")
    private String film_time;

    @Column(name = "directors")
    private String directors;

    @Column(name = "makers")
    private String makers;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "series")
    private String series;

    @Column(name = "cover_url")
    private String cover_url;

    @Column(name = "cover", columnDefinition = "mediumblob")
    private byte[] cover;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "av_gallery_join_tbl", joinColumns = @JoinColumn(name = "id"))
    private List<AVGalleryEmb> gallery;
}
