package com.dx.avserver.entity.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AVGalleryEmb implements Serializable {
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
