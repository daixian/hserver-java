package com.dx.avserver.controller;

import com.dx.avserver.dao.AVInfoDao;
import com.dx.avserver.dto.av_infoDto;
import com.dx.avserver.entity.av_info;
import com.dx.avserver.mapper.av_infoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController     //等同于同时加上了@Controller和@ResponseBody
public class HelloController {

    @Autowired
    private AVInfoDao mAVInfoDao;

    /**
     * 访问形式:http://127.0.0.1:12002/info/TGGP-089
     * @param avid
     * @return
     */
    @RequestMapping(value = {"/info/{avid}",}, method = RequestMethod.GET)
    public av_infoDto say(@PathVariable("avid") String avid) {
        log.info("进入了回调！av_id:" + avid);
        av_info info = mAVInfoDao.findOneByavid(avid);
        av_infoDto dto = av_infoMapper.INSTANCE.toDto(info);
        return dto;
    }
}
