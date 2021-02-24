package com.xiaobu.base.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2020/12/1 11:07
 * @description
 */
public class IdUtils {


    /**
     * 功能描述: 随机生成10位的数字
     *
     * @return java.lang.String
     * @author xiaobu
     * @date 2020/12/1 11:28
     * @version 1.0
     */
    public synchronized static String getPrimaryKey() {
        StringBuilder primaryKey = new StringBuilder();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            int index = threadLocalRandom.nextInt(10);
            primaryKey.append(index);
        }
        return primaryKey.toString();
    }


    public static void main(String[] args) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        Set<Long> longSet = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            long id = threadLocalRandom.nextLong(10000000000L);
            longSet.add(id);
        }
        System.out.println("longSet.size() = " + longSet.size());
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            String primaryKey = getPrimaryKey();
            System.out.println("primaryKey = " + primaryKey);
            strings.add(primaryKey);
        }
        System.out.println("strings.size() = " + strings.size());
    }
}
