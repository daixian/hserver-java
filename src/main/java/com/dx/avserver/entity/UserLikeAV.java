package com.dx.avserver.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 用户添加的喜欢的av记录
 */
@Data
@Entity
@Table(name = "user_like_av_tbl")
public class UserLikeAV implements Serializable {
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
     * 评级等级从1-5星
     */
    @Column(name = "stars")
    int stars;

    /**
     * 评价备注
     */
    @Column(name = "remark")
    String remark;

    /**
     * 记录时间
     */
    @Column(name = "add_date")
    private LocalDateTime addDate;

}
