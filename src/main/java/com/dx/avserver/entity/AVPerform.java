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
 * av的演出关系表
 */
@Data
@Entity
@Table(name = "av_perform_tbl")
public class AVPerform implements Serializable {

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
     * 演员名
     */
    @Column(name = "performer", nullable = false)
    String performer;

}