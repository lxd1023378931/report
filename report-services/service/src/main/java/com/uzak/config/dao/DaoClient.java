package com.uzak.config.dao;

import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2018/9/1.
 */
@Service
public class DaoClient {

    @Autowired
    DataSource dataSource;

    @Bean
    public org.nutz.dao.Dao getDao() {
        return new NutDao(dataSource);
    }
}
