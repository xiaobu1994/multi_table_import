package com.xiaobu.mapper.secondary;
import com.xiaobu.base.mapper.MyMapper;
import com.xiaobu.entity.RandomCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2020/12/2 14:49
 * @description
 */
public interface SecondaryMapper extends MyMapper<RandomCode> {

    int insertRandomCodeList(@Param("tableName") String tableName, @Param("list") List<RandomCode> list);

}