package com.xiaobu.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2020/11/19 11:08
 * @description
 */
@Configuration
@MapperScan(basePackages = "com.xiaobu.mapper.third", sqlSessionFactoryRef = "ThirdSqlSessionFactory")
public class ThirdDataSourceConfig {

    @Bean(name = "ThirdDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.third")
    public DataSource getSecondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "ThirdSqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("ThirdDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("com/xiaobu/mapper/third/xml/*.xml"));
        return bean.getObject();// 设置mybatis的xml所在位置
    }

    @Bean("ThirdSqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(
            @Qualifier("ThirdSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }

}


