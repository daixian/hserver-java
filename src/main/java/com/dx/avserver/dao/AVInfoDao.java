package com.dx.avserver.dao;

import com.dx.avserver.entity.av_info;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AVInfoDao extends CrudRepository<av_info, String> {

    List<av_info> findByavid(String avid);

    av_info findOneByavid(String avid);
}
