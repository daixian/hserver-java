package com.dx.avserver.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * AVInfo实体附加的数据
 */
@Data
public class AVInfoJsonData {

    public AVInfoJsonData() {
        List<String> performers = new ArrayList<>();
        List<String> gallery = new ArrayList<>();
    }

    public AVInfoJsonData(List<AVPerform> listPerform, List<AVGallery> listGallery) {
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

    /**
     * 演员列表
     */
    @JSONField(name = "performers")
    public List<String> performers;

    /**
     * 预览图列表
     */
    @JSONField(name = "gallery")
    public List<String> gallery;


}
