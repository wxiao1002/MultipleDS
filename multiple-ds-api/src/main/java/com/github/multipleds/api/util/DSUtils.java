package com.github.multipleds.api.util;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.datasource.DSProcessor;
import com.github.multipleds.api.datasource.DSProcessorProvider;

import java.sql.Connection;
import java.util.Map;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class DSUtils {

    public DSUtils() {
    }



    public static String getJdbcUrl(DbType dbType, BaseConnectParam connectParam) {
        return getDatasourceProcessor(dbType).getJdbcUrl(connectParam);
    }

    public static Connection getConnection(DbType dbType, BaseConnectParam connectParam) {
        try {
            return getDatasourceProcessor(dbType).getConnection(connectParam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDatasourceDriver(DbType dbType) {
        return getDatasourceProcessor(dbType).getDatasourceDriver();
    }



    public static DSProcessor getDatasourceProcessor(DbType dbType) {
        Map<String, DSProcessor> dataSourceProcessorMap =
                DSProcessorProvider.getInstance().getDataSourceProcessorMap();
        if (!dataSourceProcessorMap.containsKey(dbType.name())) {
            throw new IllegalArgumentException("illegal datasource type");
        }
        return dataSourceProcessorMap.get(dbType.name());
    }



    public static String getDatasourceUniqueId(BaseConnectParam connectParam, DbType dbType) {
        return getDatasourceProcessor(dbType).getDatasourceUniqueId(connectParam, dbType);
    }


}
