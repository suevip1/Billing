package com.pingpongx.smb.fee.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.pingpongx.passwordcallback.DbPasswordCallback;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author orange
 * @create 2022/6/28 11:20 上午
 */
@Configuration
public class DataSourceConfig {

    @Lazy
    private final DbPasswordCallback dbPasswordCallback;

    public DataSourceConfig(DbPasswordCallback dbPasswordCallback) {
        this.dbPasswordCallback = dbPasswordCallback;
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourceOne(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setPasswordCallback( dbPasswordCallback );
        return druidDataSource;
    }

}
