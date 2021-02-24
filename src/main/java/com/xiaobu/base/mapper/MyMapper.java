
package com.xiaobu.base.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author xiaobu
 * @date 2018/11/27 17:14
 * @descprition   用来被继承
 * @version 1.0
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    // TODO: 2018/11/27
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
