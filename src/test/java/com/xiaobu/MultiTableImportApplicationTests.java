package com.xiaobu;

import com.xiaobu.entity.RandomCode;
import com.xiaobu.mapper.fifth.FifthMapper;
import com.xiaobu.mapper.fourth.FourthMapper;
import com.xiaobu.mapper.third.ThirdMapper;
import com.xiaobu.service.RandomCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiTableImportApplicationTests {

    @Resource
    private RandomCodeService randomCodeService;


    @Autowired
    private FourthMapper fourthMapper;

    @Autowired
    private ThirdMapper thirdMapper;


    @Autowired
    private FifthMapper fifthMapper;


    @Test
    public void addBatch() {
        randomCodeService.insertBatch(2);
    }


    @Test
    public void insertDatabase1() {
        for (int i = 1; i <= 40; i++) {
            randomCodeService.insertDatabase1(2, i);
        }
    }


    @Test
    public void insertDatabase2() {
        for (int i = 41; i <= 80; i++) {
            randomCodeService.insertDatabase2(2, i);
        }
    }


    @Test
    public void insertDatabase3() {
        for (int i = 41; i <= 80; i++) {
            randomCodeService.insertDatabase3(2, i);
        }
    }

    @Test
    public void insertDatabase4() {
        for (int i = 121; i <= 160; i++) {
            randomCodeService.insertDatabase4(2, i);
        }
    }


    @Test
    public void addTables() {
        List<RandomCode> randomCodes = new ArrayList<>();
        RandomCode randomCode = new RandomCode();
        randomCode.setId(1L);
        randomCodes.add(randomCode);
        fifthMapper.insertRandomCaodeList("random_code_81", randomCodes);
    }


    @Test
    public void excuteTask() {
        randomCodeService.excuteTask(2, 1);
    }


    @Test
    public void insertByRadomCode() {
        randomCodeService.insertByRandomCode(2);
    }


}
