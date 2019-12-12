package com.dx.avserver.controller;

import com.dx.avserver.dao.AVInfoDao;
import com.dx.avserver.dao.UserDao;
import com.dx.avserver.dto.AVInfoDto;
import com.dx.avserver.utils.exception.ExceptionNotFound;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController     //等同于同时加上了@Controller和@ResponseBody
public class HelloController {

    @Autowired
    HelloController(AVInfoDao aVInfoDao, UserDao userDao) {
        //如果构造函数这样写,实际使用的时候试了不写@Autowired,也能正常工作.
        this.mAVInfoDao = aVInfoDao;
        this.mUserDao = userDao;
    }

    private AVInfoDao mAVInfoDao;
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
        AVInfo info = mAVInfoDao.findOneByAvid(avid.toUpperCase());
        if (info == null)
            throw new ExceptionNotFound();
        return AVInfoMapper.INSTANCE.toDto(info);
    }

    /**
     * 访问形式:http://127.0.0.1:12002/search?avid=TGGP-089
     *
     * @param avid 番号
     * @return av信息
     */
    @RequestMapping(value = {"/search",}, method = RequestMethod.GET)
    public List<AVInfoDto> searchAVInfoWithAVID(@RequestParam("avid") String avid) {
        log.info("通过番号搜索信息！av_id:" + avid);
        List<AVInfo> list = mAVInfoDao.findFirst100ByAvidContaining(avid.toUpperCase());
        if (list.isEmpty())
            throw new ExceptionNotFound();
        return AVInfoMapper.INSTANCE.toDto(list);
    }

    /**
     * 实验测试
     *
     * @param req 客户端需要传一个json对象
     * @return 随便返回个啥
     */
    @RequestMapping(value = {"/test"}, method = RequestMethod.POST)
    public ResponseDto TestRequestBody(@RequestBody Map<String, String> req) {
        log.info("TestRequestBody():" + req);
        //这种请求里面必须在Headers里面写Content-Type为application/json
        //然后再body里面写一个json

        return ResponseDto.FAIL();
    }

    /**
     * 用户注册它的类型是form-data
     *
     * @param userID 用户账号
     * @param pwd    密码
     * @param name   用户昵称
     * @return dto
     */
    @RequestMapping(value = {"/user/signin"}, method = RequestMethod.POST)
    public ResponseDto SignIn(@RequestParam(value = "userID") String userID,
                              @RequestParam(value = "pwd") String pwd,
                              @RequestParam(value = "name") String name) {
        if (userID.isEmpty()) {
            return ResponseDto.FAIL();
        }

        User user = mUserDao.findOneByUserID(userID);
        //如果找不到这个用户记录，那么就新建一条
        if (user == null) {
            user = new User();
            user.setUserID(userID);
            user.setPassword(pwd);
            //如果昵称是空那么提供一个默认名字
            if (!name.isEmpty())
                user.setName(name);
            else
                user.setName("unnamed");

            user.setSignInDate(LocalDateTime.now());
            mUserDao.save(user);
            return ResponseDto.SUCCESS();
        }

        return ResponseDto.FAIL();
    }

    /**
     * 用户登录,登录成功的话返回一个token
     *
     * @param userID 用户账号
     * @param pwd    用户密码
     * @return dto
     */
    @RequestMapping(value = {"/user/login"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto login(@RequestParam(value = "userID") String userID,
                             @RequestParam(value = "pwd") String pwd) {
        if (userID.isEmpty()) {
            return ResponseDto.LOGIN_FAIL();
        }

        //数据库查用户
        User user = mUserDao.findOneByUserID(userID);
        if (user == null) {
            return ResponseDto.LOGIN_FAIL();
        }
        //如果密码不对
        if (!pwd.equals(user.getPassword())) {
            return ResponseDto.LOGIN_FAIL_PWD_ERROR();
        }
        //密码通过了,返回token
        String token = JwtUtils.sign(user.getUserID(), user.getName());
        if (token != null) {
            user.setLastLoginDate(LocalDateTime.now());
            mUserDao.save(user);

            Map<String, String> data = new HashMap<>();
            data.put("token", token);
            ResponseDto dto = ResponseDto.SUCCESS();
            dto.setData(data);
            return dto;
        }

        //返回登陆失败消息
        return ResponseDto.LOGIN_FAIL();
    }
}
