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
 * 用户在硬盘上已经存储有了的av文件
 */
@Data
@Entity
@Table(name = "user_storage_av_tbl")
public class UserStorageAV implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    /**
     * 用户的序号
     */
    @Column(name = "user_num", nullable = false)
    int userNum;

    /**
     * 番号
     */
    @Column(name = "av_id", nullable = false)
    String avid;


    /**
     * 硬盘文件路径
     */
    @Column(name = "path", nullable = false)
    String path;
}
