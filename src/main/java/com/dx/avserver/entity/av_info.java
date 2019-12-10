package com.dx.avserver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 对应数据库的实体
 */
@Entity
@Getter
@Setter
@Table(name = "av_info_tbl")
public class av_info implements Serializable {

    @Id //entity的
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(name = "av_id", unique = false, nullable = false)
    private String avid;

    @Column(name = "javbooks_id", unique = false)
    private int javbooks_id;

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


}
