package com.dx.avserver.controller;

import com.dx.avserver.dao.AVInfoDao;
import com.dx.avserver.dto.av_infoDto;
import com.dx.avserver.entity.av_info;
import com.dx.avserver.mapper.av_infoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController     //等同于同时加上了@Controller和@ResponseBody
public class HelloController {

    @Autowired
    private AVInfoDao mAVInfoDao;

    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public av_infoDto say() {
        log.info("进入了回调！");

        av_info info = mAVInfoDao.findOneByavid("TGGP-089");
        return av_infoMapper.INSTANCE.toDto(info);
    }
}
