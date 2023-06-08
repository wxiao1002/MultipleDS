package com.github.multipleds.api.datasource;



import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DSProcessor {


    /**
     * 获取ds 的唯一id
     * @param connectParam BaseConnectParam
     * @param dbType DbType
     * @return String UniqueId
     */
    String getDatasourceUniqueId(BaseConnectParam connectParam, DbType dbType);


    /**
     * 获取数据源 Driver
     * @return String
     */
    String getDatasourceDriver();

    /**
     * 获取validate sql
     * @return String
     */
    String getValidateQuery();

    /**
     * 获取jdbc url
     * @param connectParam  BaseConnectParam
     */
    String getJdbcUrl(BaseConnectParam connectParam);

    /**
     * 获取连接
     * @return {@link Connection}
     */
    Connection getConnection(BaseConnectParam connectParam) throws ClassNotFoundException, SQLException, IOException;

    /**
     * 测试连接
     * @param connectParam BaseConnectParam
     * @return Boolean
     */
    default boolean testConnection(BaseConnectParam connectParam) {
        return false;
    }


    DbType getDbType();


    DSProcessor create();
}
