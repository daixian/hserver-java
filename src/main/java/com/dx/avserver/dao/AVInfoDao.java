package com.dx.avserver.dao;

import com.dx.avserver.entity.AVInfo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AVInfoDao extends CrudRepository<AVInfo, String> {

    List<AVInfo> findByAvid(String avid);

    AVInfo findOneByAvid(String avid);
}
