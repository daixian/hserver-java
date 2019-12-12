package com.dx.avserver.dao;

import com.dx.avserver.entity.AVGallery;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AVGalleryDao extends CrudRepository<AVGallery, String> {

    /**
     * 根据一个番号找到所有的预览图
     *
     * @param avid 番号
     * @return 预览图列表
     */
    List<AVGallery> findByAvid(String avid);
}
