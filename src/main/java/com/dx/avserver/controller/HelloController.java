package com.dx.avserver.controller;

import com.dx.avserver.dao.AVInfoDao;
import com.dx.avserver.dao.UserDao;
import com.dx.avserver.dto.AVInfoDto;
import com.dx.avserver.dto.ResponseDto;
import com.dx.avserver.entity.User;
import com.dx.avserver.entity.AVInfo;
import com.dx.avserver.mapper.AVInfoMapper;
import com.dx.avserver.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController     //等同于同时加上了@Controller和@ResponseBody
public class HelloController {

    @Autowired
    private AVInfoDao mAVInfoDao;

    @Autowired
    private UserDao mUserDao;

    /**
     * 访问形式:http://127.0.0.1:12002/info/TGGP-089
     *
     * @param avid 番号
     * @return av信息
     */
    @RequestMapping(value = {"/info/{avid}",}, method = RequestMethod.GET)
    public AVInfoDto getAVInfoWithAVID(@PathVariable("avid") String avid) {
        log.info("通过番号查询信息！av_id:" + avid);
        AVInfo info = mAVInfoDao.findOneByavid(avid);
        return AVInfoMapper.INSTANCE.toDto(info);
    }

    /**
     * 用户注册
     *
     * @param req 用户传上来的内容
     * @return dto
     */
    @RequestMapping(value = {"/user/signin"}, method = RequestMethod.POST)
    public ResponseDto SignIn(@RequestBody Map<String, String> req) {
        String userID = req.get("userID");
        String pwd = req.get("pwd");
        String name = req.get("name");

        ResponseDto dto = new ResponseDto();

        User user = mUserDao.findOneByuserID(userID);
        //如果找不到这个用户记录，那么就新建一条
        if (user == null) {
            user = new User();
            user.setUserID(userID);
            user.setPassword(pwd);
            user.setName(name);
            user.setSignInDate(LocalDateTime.now());
            mUserDao.save(user);
            dto.setStatus(ResponseDto.Status.SUCCESS);
            return dto;
        }

        return dto;
    }

    /**
     * 用户登录,登录成功的话返回一个token
     *
     * @param req 用户传上来的内容
     * @return dto
     */
    @RequestMapping(value = {"/user/login"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto login(@RequestBody Map<String, String> req) {
        String userID = req.get("userID");
        String pwd = req.get("pwd");
        ResponseDto dto = new ResponseDto();

        //数据库查用户
        User user = mUserDao.findOneByuserID(userID);
        if (user == null) {
            return dto;
        }
        //如果密码不对
        if (pwd.equals(user.getPassword())) {
            dto.setStatus(ResponseDto.Status.LOGIN_FAIL_PWD_ERROR);
            return dto;
        }
        //密码通过了,返回token
        String token = JwtUtils.sign(user.getName(), user.getUserID());
        if (token != null) {
            Map<String, String> data = new HashMap<>();
            data.put("token", token);
            dto.setData(data);
            dto.setStatus(ResponseDto.Status.SUCCESS);
            return dto;
        }

        //返回登陆失败消息
        dto.setStatus(ResponseDto.Status.LOGIN_FAIL);
        return dto;
    }
}
