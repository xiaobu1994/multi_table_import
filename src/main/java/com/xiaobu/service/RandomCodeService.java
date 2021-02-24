package com.xiaobu.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xiaobu.base.util.IdUtils;
import com.xiaobu.entity.RandomCode;
import com.xiaobu.entity.vo.PrimaryTask;
import com.xiaobu.entity.vo.RandomCodeVo;
import com.xiaobu.mapper.fifth.FifthMapper;
import com.xiaobu.mapper.fourth.FourthMapper;
import com.xiaobu.mapper.primary.PrimaryMapper;
import com.xiaobu.mapper.secondary.SecondaryMapper;
import com.xiaobu.mapper.third.ThirdMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2020/12/8 14:13
 * @description
 */
@Service
@Slf4j
public class RandomCodeService {
    private static final AtomicLong INDEX = new AtomicLong(0);
    private static final String TABLE_PRE = "random_code_";
    //private static final Long COUNT = 5000L;
    private static final Long COUNT = 100000000L;
    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%s").build();


    @Autowired
    private PrimaryMapper primaryMapper;

    @Autowired
    private SecondaryMapper secondaryMapper;

    @Autowired
    private ThirdMapper thirdMapper;

    @Autowired
    private FourthMapper fourthMapper;

    @Autowired
    private FifthMapper fifthMapper;

    public Long insertBatch(final int threadNum) {
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        long starttime = System.currentTimeMillis();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, 15, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000), threadFactory);
        AtomicLong insertCount = new AtomicLong();
        for (int k = 1; k <= threadNum; k++) {
            final List<RandomCode>[] randomCodeList = new List[]{new ArrayList<>()};
            executor.execute(() -> {
                try {
                    int count = 0;
                    for (long i = 1; i <= COUNT / threadNum; i++) {
                        insertCount.getAndIncrement();
                        long id = INDEX.incrementAndGet();
                        RandomCode randomCode = new RandomCode();
                        randomCode.setId(id);
                        randomCodeList[0].add(randomCode);
                        if (i % 2500 == 0) {
                            count++;
                            primaryMapper.insertRandomCaodeList("random_code_1", randomCodeList[0]);
                            randomCodeList[0] = null;
                            randomCodeList[0] = new ArrayList<>();
                            log.info("提交第{}次", count);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();

                }
            });
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - starttime;
            System.out.println(threadNum + "个线程花费时间:" + spendtime / 1000 + "S");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return insertCount.get();
    }


    /**
     * @param threadNum 线程个数
     * @param index     表名下标
     */
    public void insertDatabase1(int threadNum, int index) {
        AtomicInteger atomicIndex = new AtomicInteger(index);
        long indexNum = (index - 1) * COUNT + 1;
        AtomicLong atomicI = new AtomicLong(indexNum);
        AtomicLong atomicId = new AtomicLong(indexNum);
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        long startTime = System.currentTimeMillis();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000), threadFactory);
        AtomicLong count = new AtomicLong(0);
        for (int k = 1; k <= threadNum; k++) {
            final List<RandomCode>[] randomCodeList = new List[]{new ArrayList<>()};
            executor.execute(() -> {
                try {
                    String tableName = TABLE_PRE + atomicIndex.get();
                    for (long i = atomicI.get(); i < atomicI.get() + COUNT / threadNum; i++) {
                        long id = atomicId.getAndIncrement();
                        RandomCode randomCode = new RandomCode();
                        randomCode.setId(id);
                        randomCodeList[0].add(randomCode);
                        if (i % 10000 == 0) {
                            count.getAndIncrement();
                            primaryMapper.insertRandomCaodeList(tableName, randomCodeList[0]);
                            randomCodeList[0] = null;
                            randomCodeList[0] = new ArrayList<>();
                            log.info("往表{}提交第{}次数据", tableName, count);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();

                }
            });
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - startTime;
            System.out.println(threadNum + "个线程花费时间:" + spendtime / 1000 + "S");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


    public void insertDatabase2(int threadNum, int index) {
        AtomicInteger atomicIndex = new AtomicInteger(index);
        long indexNum = (index - 1) * COUNT + 1;
        AtomicLong atomicI = new AtomicLong(indexNum);
        AtomicLong atomicId = new AtomicLong(indexNum);
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        long startTime = System.currentTimeMillis();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000), threadFactory);
        AtomicLong count = new AtomicLong(0);
        for (int k = 1; k <= threadNum; k++) {
            final List<RandomCode>[] randomCodeList = new List[]{new ArrayList<>()};
            executor.execute(() -> {
                try {
                    String tableName = TABLE_PRE + atomicIndex.get();
                    for (long i = atomicI.get(); i < atomicI.get() + COUNT / threadNum; i++) {
                        long id = atomicId.getAndIncrement();
                        RandomCode randomCode = new RandomCode();
                        randomCode.setId(id);
                        randomCodeList[0].add(randomCode);
                        if (i % 10000 == 0) {
                            count.getAndIncrement();
                            secondaryMapper.insertRandomCodeList(tableName, randomCodeList[0]);
                            randomCodeList[0] = null;
                            randomCodeList[0] = new ArrayList<>();
                            log.info("往表{}提交第{}次数据", tableName, count);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();

                }
            });
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - startTime;
            System.out.println(threadNum + "个线程花费时间:" + spendtime / 1000 + "S");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    public void insertDatabase3(int threadNum, int index) {
        AtomicInteger atomicIndex = new AtomicInteger(index);
        long indexNum = (index - 1) * COUNT + 1;
        AtomicLong atomicI = new AtomicLong(indexNum);
        AtomicLong atomicId = new AtomicLong(indexNum);
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        long startTime = System.currentTimeMillis();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000), threadFactory);
        AtomicLong count = new AtomicLong(0);
        for (int k = 1; k <= threadNum; k++) {
            final List<RandomCode>[] randomCodeList = new List[]{new ArrayList<>()};
            executor.execute(() -> {
                try {
                    String tableName = TABLE_PRE + atomicIndex.get();
                    for (long i = atomicI.get(); i < atomicI.get() + COUNT / threadNum; i++) {
                        long id = atomicId.getAndIncrement();
                        RandomCode randomCode = new RandomCode();
                        randomCode.setId(id);
                        randomCodeList[0].add(randomCode);
                        if (i % 10000 == 0) {
                            count.getAndIncrement();
                            thirdMapper.insertRandomCodeList(tableName, randomCodeList[0]);
                            randomCodeList[0] = null;
                            randomCodeList[0] = new ArrayList<>();
                            log.info("往表{}提交第{}次数据", tableName, count);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();

                }
            });
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - startTime;
            System.out.println(threadNum + "个线程花费时间:" + spendtime / 1000 + "S");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


    public void insertDatabase4(int threadNum, int index) {
        AtomicInteger atomicIndex = new AtomicInteger(index);
        long indexNum = (index - 1) * COUNT + 1;
        AtomicLong atomicI = new AtomicLong(indexNum);
        AtomicLong atomicId = new AtomicLong(indexNum);
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        long startTime = System.currentTimeMillis();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000), threadFactory);
        AtomicLong count = new AtomicLong(0);
        for (int k = 1; k <= threadNum; k++) {
            final List<RandomCode>[] randomCodeList = new List[]{new ArrayList<>()};
            executor.execute(() -> {
                try {
                    String tableName = TABLE_PRE + atomicIndex.get();
                    for (long i = atomicI.get(); i < atomicI.get() + COUNT / threadNum; i++) {
                        long id = atomicId.getAndIncrement();
                        RandomCode randomCode = new RandomCode();
                        randomCode.setId(id);
                        randomCodeList[0].add(randomCode);
                        if (i % 10000 == 0) {
                            count.getAndIncrement();
                            fourthMapper.insertRandomCodeList(tableName, randomCodeList[0]);
                            randomCodeList[0] = null;
                            randomCodeList[0] = new ArrayList<>();
                            log.info("往表{}提交第{}次数据", tableName, count);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();

                }
            });
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - startTime;
            System.out.println(threadNum + "个线程花费时间:" + spendtime / 1000 + "S");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


    public void insertDatabase5(int threadNum, int index) {
        AtomicInteger atomicIndex = new AtomicInteger(index);
        long indexNum = (index - 1) * COUNT + 1;
        AtomicLong atomicI = new AtomicLong(indexNum);
        AtomicLong atomicId = new AtomicLong(indexNum);
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        long startTime = System.currentTimeMillis();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000), threadFactory);
        AtomicLong count = new AtomicLong(0);
        for (int k = 1; k <= threadNum; k++) {
            final List<RandomCode>[] randomCodeList = new List[]{new ArrayList<>()};
            executor.execute(() -> {
                try {
                    String tableName = TABLE_PRE + atomicIndex.get();
                    for (long i = atomicI.get(); i < atomicI.get() + COUNT / threadNum; i++) {
                        long id = atomicId.getAndIncrement();
                        RandomCode randomCode = new RandomCode();
                        randomCode.setId(id);
                        randomCodeList[0].add(randomCode);
                        if (i % 10000 == 0) {
                            count.getAndIncrement();
                            fifthMapper.insertRandomCaodeList(tableName, randomCodeList[0]);
                            randomCodeList[0] = null;
                            randomCodeList[0] = new ArrayList<>();
                            log.info("往表{}提交第{}次数据", tableName, count);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();

                }
            });
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - startTime;
            System.out.println(threadNum + "个线程花费时间:" + spendtime / 1000 + "S");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


    public void excuteTask(int threadNum, int index) {
        AtomicInteger atomicIndex = new AtomicInteger(index);
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        long indexNum = (index - 1) * COUNT + 1;
        AtomicLong atomicId = new AtomicLong(indexNum);
        long startTime = System.currentTimeMillis();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000), threadFactory);
        for (int k = 1; k <= threadNum; k++) {
            String tableName = TABLE_PRE + atomicIndex.get();
            PrimaryTask primaryTask = new PrimaryTask(primaryMapper, tableName, atomicId);
            executor.execute(primaryTask);
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - startTime;
            System.out.println(threadNum + "个线程花费时间:" + spendtime / 1000 + "S");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    //@Transactional(rollbackFor = Exception.class)
    public void insertByRandomCode(final int threadNum) {
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        long starttime = System.currentTimeMillis();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, 15, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000), threadFactory);
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        AtomicLong insertCount = new AtomicLong();
        AtomicLong reapetCount = new AtomicLong();
        for (int k = 1; k <= threadNum; k++) {
            executor.execute(() -> {
                try {
                    for (long i = 1; i <= COUNT / threadNum; i++) {
                        insertCount.incrementAndGet();
                        String id = IdUtils.getPrimaryKey();
                        RandomCodeVo randomCodeVo = primaryMapper.selectRandomCodeVoById(id);
                        if(randomCodeVo==null){
                            RandomCodeVo randomCode = new RandomCodeVo();
                            randomCode.setId(id);
                            primaryMapper.RandomCodeVo(randomCode);
                            System.out.println("提交了  " + insertCount+" 次！");
                            //log.info("提交了第{}次", insertCount);
                        }else {
                            reapetCount.incrementAndGet();
                            log.info("重复了第{}次", reapetCount);
                        }

                    }

                } catch (Exception e) {
                    log.info("错误{}重复了第{}次", e.getMessage(),reapetCount);
                } finally {
                    cdl.countDown();

                }
            });
        }
        try {
            cdl.await();
            long spendtime = System.currentTimeMillis() - starttime;
            System.out.println(threadNum + "个线程花费时间:" + spendtime / 1000 + "S");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        insertCount.get();
    }

}
