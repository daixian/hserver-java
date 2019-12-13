package com.dx.avserver.dto;

import com.dx.avserver.entity.AVGallery;
import com.dx.avserver.entity.AVPerform;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> performers;
    private String keywords;
    private String releases_time;
    private String film_time;
    private String directors;
    private String makers;
    private String issuer;
    private String series;
    private String cover_url;
    private List<String> gallery;
}
