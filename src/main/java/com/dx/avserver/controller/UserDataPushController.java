package com.dx.avserver.controller;

import com.dx.avserver.dao.AVInfoDao;
import com.dx.avserver.dao.UserDao;
import com.dx.avserver.dto.AVInfoDto;
import com.dx.avserver.entity.AVInfo;
import com.dx.avserver.mapper.AVInfoMapper;
import com.dx.avserver.utils.exception.ExceptionNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController     //等同于同时加上了@Controller和@ResponseBody
public class UserDataPushController {

    @Autowired
    UserDataPushController(AVInfoDao aVInfoDao, UserDao userDao) {
        //如果构造函数这样写,实际使用的时候试了不写@Autowired,也能正常工作.
        this.mAVInfoDao = aVInfoDao;
        this.mUserDao = userDao;
    }

    private AVInfoDao mAVInfoDao;
    private UserDao mUserDao;


    @RequestMapping(value = {"/av/pull/new",}, method = RequestMethod.GET)
    public List<AVInfoDto> pullNewestAV() {
        List<AVInfo> list = mAVInfoDao.findFirst100ByJavBooksIdGreaterThanOrderByJavBooksIdDesc(226872);

        return null;
    }

    @RequestMapping(value = {"/av/pull/jav",}, method = RequestMethod.GET)
    public List<AVInfoDto> pullNewAVWithJavID(@RequestParam("id") int javId) {
        log.info("使用javid拉取" + javId);

        return null;
    }
}
