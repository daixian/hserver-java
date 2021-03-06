package com.dx.avserver.dao;

import com.dx.avserver.entity.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, String> {

    List<User> findByUserID(String userID);

    User findOneByUserID(String userID);
}
