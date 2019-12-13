package com.dx.avserver.entity;

import com.alibaba.fastjson.JSON;
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
import javax.persistence.Transient;

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

    @Column(name = "json_data",columnDefinition = "text")
    private String jsonDataText;

    @Transient
    private AVInfoJsonData jsonDataObj;

    public void serializeJson() {
        if (jsonDataObj == null) {
            jsonDataObj = new AVInfoJsonData();
        }
        jsonDataText = JSON.toJSONString(jsonDataObj);
    }

    public void deserializeJson() {
        if (jsonDataText == null || jsonDataText.isEmpty())
            jsonDataObj = new AVInfoJsonData();
        jsonDataObj = JSON.parseObject(jsonDataText, AVInfoJsonData.class);
    }
}
