package com.xiaobu.mapper.primary;

import java.util.List;

import com.xiaobu.base.mapper.MyMapper;
import com.xiaobu.entity.RandomCode;
import com.xiaobu.entity.vo.RandomCodeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2020/12/2 14:49
 * @description
 */
public interface PrimaryMapper extends MyMapper<RandomCode> {

    int insertRandomCaodeList(@Param("tableName") String tableName, @Param("list") List<RandomCode> list);

    int insertRandomCode(@Param("randomCode") RandomCode randomCode);

    int RandomCodeVo(@Param("randomCodeVo") RandomCodeVo randomCodeVo);

    RandomCodeVo selectRandomCodeVoById(@Param("id")String id);




}