package com.dx.avserver.dao;

import com.dx.avserver.entity.AVPerform;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AVPerformDao extends CrudRepository<AVPerform, String> {

    /**
     * 根据一个番号找到所有的演员
     *
     * @param avid 番号
     * @return 所有的演员列表
     */
    List<AVPerform> findByAvid(String avid);
}
