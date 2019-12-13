package com.dx.avserver;

import com.dx.avserver.dao.AVInfoDao;
import com.dx.avserver.dto.AVInfoDto;
import com.dx.avserver.entity.AVInfo;
import com.dx.avserver.entity.embeddable.AVGalleryEmb;
import com.dx.avserver.mapper.AVInfoMapper;

import org.assertj.core.api.Assert;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class AvserverApplicationTests {

//    @Autowired
//    private EntityManagerFactory entityManagerFactory;

//    public Session getSession() {
//        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
//    }

    @Autowired
    private AVInfoDao mAVInfoDao;


    @Test
    void contextLoads() {
    }

//    @Test
//    void AVInfoMapperTest() {
//        AVInfo info = new AVInfo();
//        info.setAvid("TEST-002");
//        info.setJavBooksId(1000002);
//
//        info.setTitle("groovin’ 超ハイレグキャンギャルDANCE GROO-051");
//        info.setKeywords("GROO-051,----,DMM独家");
//        info.setReleases_time("releases_time");
//        info.setCover_url("https://pics.dmm.co.jp/digital/video/groo00051/groo00051pl.jpg");
//        List<AVGalleryEmb> gallery = new ArrayList<AVGalleryEmb>();
//        for (int i = 0; i < 4; i++) {
//            AVGalleryEmb emb = new AVGalleryEmb();
//            emb.setImageUrl("url:" + i);
//            gallery.add(emb);
//        }
//        info.setGallery(gallery);
//
//        AVInfoDto dto = AVInfoMapper.INSTANCE.toDto(info);
//        assertTrue("读取到的结果图片url=3", dto.getGallery().size() == info.getGallery().size());
//    }


//    @Test
//    void testDBAVInfoDao() {
//        AVInfo info0 = mAVInfoDao.findOneByAvid("TEST-002");
//        if (info0 != null)
//            mAVInfoDao.delete(info0);
//
//        AVInfo info = new AVInfo();
//        info.setAvid("TEST-002");
//        info.setJavBooksId(1000002);
//
//        info.setTitle("groovin’ 超ハイレグキャンギャルDANCE GROO-051");
//
//        info.setKeywords("GROO-051,----,DMM独家");
//        info.setReleases_time("releases_time");
//        info.setCover_url("https://pics.dmm.co.jp/digital/video/groo00051/groo00051pl.jpg");
//        List<AVGalleryEmb> gallery = new ArrayList<AVGalleryEmb>();
//        for (int i = 0; i < 4; i++) {
//            AVGalleryEmb emb = new AVGalleryEmb();
//            emb.setImageUrl("url:" + i);
//            gallery.add(emb);
//        }
//        info.setGallery(gallery);
//        mAVInfoDao.save(info);
//        AVInfo info2 = mAVInfoDao.findOneByAvid("TEST-002");
//        assertTrue("读取到的结果图片url=3", info2.getGallery().size() == 4);
//    }


//    @Test
//    void testDBAVInfo() {
//        Session session = getSession();
//        Transaction transaction = session.beginTransaction();
//        AVInfo info = new AVInfo();
//        info.setAvid("TEST-001");
//        info.setJavBooksId(226918);
//
//        info.setTitle("groovin’ 超ハイレグキャンギャルDANCE GROO-051");
//
//        info.setKeywords("GROO-051,----,DMM独家");
//        info.setReleases_time("releases_time");
//        info.setCover_url("https://pics.dmm.co.jp/digital/video/groo00051/groo00051pl.jpg");
//        List<String> gallery = new ArrayList<String>();
//        gallery.add("url:11111111111111111111111111111");
//        gallery.add("url:22222222222222222222222222222");
//        gallery.add("url:333333333333333333333");
//        gallery.add("url:444444444444444444444444444");
////        info.setGallery(gallery);
//        session.save(info);
//        transaction.commit();
//        session.close();
//    }
}
