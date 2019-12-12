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
 * 演员头像表
 */
@Data
@Entity
@Table(name = "performer_tbl")
public class Performer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    /**
     * 演员名
     */
    @Column(name = "performer", nullable = false)
    String performer;

    /**
     * 头像图片url
     */
    @Column(name = "img_url", nullable = false)
    String imageUrl;

    /**
     * 头像图片数据
     */
    @Column(name = "img_data")
    byte[] imageData;
}