package com.xiaobu.controller;

import com.xiaobu.service.RandomCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2020/12/10 9:00
 * @description
 */
@RestController
@RequestMapping("randomCode")
public class RandomCodeController {

    @Autowired
    private RandomCodeService randomCodeService;


    @GetMapping("insertDatabase1")
    public String insertTablesBatch(int threadNum) {
        for (int i = 1; i <= 40; i++) {
            randomCodeService.insertDatabase1(threadNum, i);
        }
        return "OK";
    }

    @GetMapping("insertDatabase2")
    public String insertDatabase2(int threadNum) {
        for (int i = 41; i <= 80; i++) {
            randomCodeService.insertDatabase2(threadNum, i);
        }
        return "OK";
    }

    @GetMapping("insertDatabase3")
    public String insertDatabase3(int threadNum) {
        for (int i = 81; i <= 120; i++) {
            randomCodeService.insertDatabase3(threadNum, i);
        }
        return "OK";
    }

    @GetMapping("insertDatabase4")
    public String insertDatabase4(int threadNum) {
        for (int i = 121; i <= 160; i++) {
            randomCodeService.insertDatabase4(threadNum, i);
        }
        return "OK";
    }

    @GetMapping("insertDatabase5")
    public String insertDatabase5(int threadNum) {
        for (int i = 161; i <= 200; i++) {
            randomCodeService.insertDatabase5(threadNum, i);
        }
        return "OK";
    }


    @GetMapping("insertByRandomCode")
    public String insertByRandomCode(int threadNum) {
        randomCodeService.insertByRandomCode(threadNum);
        return "OK";
    }


}
