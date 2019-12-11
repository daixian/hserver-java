package com.dx.avserver.dto;

import lombok.Data;

/**
 * 一项av信息
 */
@Data
public class AVInfoDto {
    private int id;
    private String av_id;
    private int javbooks_id;
    private String title;
    private String keywords;
    private String releases_time;
    private String film_time;
    private String directors;
    private String makers;
    private String issuer;
    private String series;
    private String cover_url;
}
