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


    public void setSupplementData(List<AVPerform> listPerform, List<AVGallery> listGallery) {
        if (listPerform != null && !listPerform.isEmpty()) {
            if (performers == null) {
                performers = new ArrayList<>();
            } else {
                performers.clear();
            }
            for (AVPerform item : listPerform) {
                performers.add(item.getPerformer());
            }
        }
        if (listGallery != null && !listGallery.isEmpty()) {
            if (gallery == null) {
                gallery = new ArrayList<>();
            } else {
                gallery.clear();
            }
            for (AVGallery item : listGallery) {
                gallery.add(item.getImageUrl());
            }
        }

    }
}
