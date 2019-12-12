package com.dx.avserver.controller;

import com.dx.avserver.dao.AVGalleryDao;
import com.dx.avserver.dao.AVInfoDao;
import com.dx.avserver.dao.AVPerformDao;
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
    UserDataPushController(AVInfoDao aVInfoDao, UserDao userDao,
                           AVGalleryDao aVGalleryDao, AVPerformDao aVPerformDao) {
        //如果构造函数这样写,实际使用的时候试了不写@Autowired,也能正常工作.
        this.mAVInfoDao = aVInfoDao;
        this.mUserDao = userDao;
        this.mAVGalleryDao = aVGalleryDao;
        this.mAVPerformDao = aVPerformDao;
    }

    private AVInfoDao mAVInfoDao;
    private UserDao mUserDao;
    private AVGalleryDao mAVGalleryDao;
    private AVPerformDao mAVPerformDao;

    /**
     * 实验:根据一个avid搜得到av信息
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
        return _AVInfoToDto(info);
    }

    /**
     * 实验:根据一个avid搜索多条av
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
        return _AVInfoListToDto(list);
    }


    /**
     * 获取比给出的这个javId要更新的av
     * http://127.0.0.1:12002/av/pull/new?javid=0
     *
     * @param javId javbooks的网页id号
     * @return dto
     */
    @RequestMapping(value = {"/av/pull/new",}, method = RequestMethod.GET)
    public List<AVInfoDto> pullAVNewThenJavId(@RequestParam("javid") int javId) {
        List<AVInfo> list = mAVInfoDao.findFirst100ByJavBooksIdGreaterThanOrderByJavBooksIdDesc(javId);
        if (list.isEmpty())
            throw new ExceptionNotFound();
        return _AVInfoListToDto(list);
    }

    /**
     * 获取比给出的这个javId要更旧的av
     * http://127.0.0.1:12002/av/pull/old?javid=0
     *
     * @param javId javbooks的网页id号
     * @return dto
     */
    @RequestMapping(value = {"/av/pull/old",}, method = RequestMethod.GET)
    public List<AVInfoDto> pullAVOldThenJavId(@RequestParam("javid") int javId) {
        List<AVInfo> list = mAVInfoDao.findFirst100ByJavBooksIdLessThanOrderByJavBooksIdDesc(javId);
        if (list.isEmpty())
            throw new ExceptionNotFound();
        return _AVInfoListToDto(list);
    }

    //----------------------------------------------------------------------------------------------

    //私有函数: info到dto的转换,填充其他的信息
    private AVInfoDto _AVInfoToDto(AVInfo info) {
        AVInfoDto dto = AVInfoMapper.INSTANCE.toDto(info);
        dto.setSupplementData(
                mAVPerformDao.findByAvid(info.getAvid()),
                mAVGalleryDao.findByAvid(info.getAvid()));
        return dto;
    }

    //私有函数: info到dto的转换,填充其他的信息
    private List<AVInfoDto> _AVInfoListToDto(List<AVInfo> list) {
        List<AVInfoDto> dto = AVInfoMapper.INSTANCE.toDto(list);
        for (AVInfoDto item : dto) {
            item.setSupplementData(
                    mAVPerformDao.findByAvid(item.getAv_id()),
                    mAVGalleryDao.findByAvid(item.getAv_id()));
        }
        return dto;
    }
}
