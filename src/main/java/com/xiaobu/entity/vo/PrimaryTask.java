package com.xiaobu.entity.vo;

import com.xiaobu.entity.RandomCode;
import com.xiaobu.mapper.primary.PrimaryMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2020/12/8 13:46
 * @description
 */
@Slf4j
public class PrimaryTask implements Runnable {
    /**
     * 数据访问层
     */
    private PrimaryMapper primaryMapper;

    /**
     * 插入的数据
     */
    private List<RandomCode> list;

    /**
     * 表名
     */
    private String tabelName;


    private AtomicLong count;

    public PrimaryTask(PrimaryMapper primaryMapper, String tabelName, AtomicLong count) {
        this.primaryMapper = primaryMapper;
        this.tabelName = tabelName;
        this.count = count;
    }


    @Override
    public void run() {
        this.list = new ArrayList<>();
        AtomicLong insertAcount = new AtomicLong(0);
        for (long i = this.count.get(); i <= this.count.get() + 50000000 / 2; i++) {
            RandomCode randomCode = new RandomCode();
            randomCode.setId(this.count.getAndIncrement());
            this.list.add(randomCode);
            if (i % 10000 == 0) {
                insertAcount.incrementAndGet();
                try {
                    this.primaryMapper.insertRandomCaodeList(this.tabelName, this.list);
                    log.info("在{}表第{}批次插入成功", this.tabelName, insertAcount.get());
                } catch (Exception e) {
                    log.info("在{}表第{}批次插入失败", this.tabelName, insertAcount.get());
                }
                this.list = null;
                this.list = new ArrayList<>();
            }
        }

    }
}
