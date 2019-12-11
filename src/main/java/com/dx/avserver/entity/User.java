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

@Data
@Entity
@Table(name = "user_tbl")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(name = "user_id", unique = true, nullable = false)
    String userID;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "name")
    String name;

    /**
     * 注册时间
     */
    @Column(name = "sign_in_date")
    private LocalDateTime signInDate;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;
}
