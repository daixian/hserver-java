package com.dx.avserver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * av的多项预览图地址
 */
@Data
@Entity
@Table(name = "av_gallery_tbl")
public class AVGallery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    /**
     * 番号
     */
    @Column(name = "av_id", nullable = false)
    String avid;

    /**
     * 一项图片url
     */
    @Column(name = "img_url", nullable = false)
    String imageUrl;

    /**
     * 一项图片byte数据
     */
    @Column(name = "img_data")
    byte[] imageData;
}
