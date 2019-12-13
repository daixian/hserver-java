package com.dx.avserver.dao;

import com.dx.avserver.entity.AVInfo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AVInfoDao extends CrudRepository<AVInfo, String> {

    List<AVInfo> findByAvid(String avid);

    AVInfo findOneByAvid(String avid);

    //注意不需要加单引号',会自动添加
    @Query("SELECT a FROM AVInfo a WHERE a.avid LIKE %:avid%")
    List<AVInfo> searchByAvid(@Param("avid") String avid);

    //这个方法等于上面的方法了 https://stackoverflow.com/questions/25362540/like-query-in-spring-jparepository
    //官方详细文档: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<AVInfo> findFirst100ByAvidContaining(String avid);


    List<AVInfo> findFirst100ByJavBooksIdGreaterThanOrderByJavBooksIdDesc(int javBooksId);

    List<AVInfo> findFirst100ByJavBooksIdLessThanOrderByJavBooksIdDesc(int javBooksId);

    List<AVInfo> findFirst1000ByJavBooksIdGreaterThanOrderByJavBooksIdDesc(int javBooksId);

    List<AVInfo> findFirst1000ByJavBooksIdLessThanOrderByJavBooksIdDesc(int javBooksId);
}
