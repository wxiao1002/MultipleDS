package com.github.multipleds.api.datasource;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.util.DSUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.util.CollectionUtils;

/**
 * 数据源 provider
 * @author wang xiao
 * date 2023/6/8
 */
public class JDBCDSProvider {


    public static HikariDataSource createJDBCDataSource(BaseConnectParam baseConnectParam, DbType dbType){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(baseConnectParam.getDriverClassName());
        config.setJdbcUrl(DSUtils.getJdbcUrl(dbType, baseConnectParam));
        config.setUsername(baseConnectParam.getUsername());
        config.setPassword(baseConnectParam.getPassword());
        config.setMinimumIdle(baseConnectParam.getMinimumIdle());
        config.setMaximumPoolSize(baseConnectParam.getMaximumPoolSize());
        config.setConnectionTestQuery(baseConnectParam.getValidateQuery());
        if (!CollectionUtils.isEmpty(baseConnectParam.getOtherParameters())) {
            baseConnectParam.getOtherParameters().forEach(config::addDataSourceProperty);
        }
        return new HikariDataSource(config);
    }



}
