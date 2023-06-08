package com.github.multipleds.db2;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.datasource.BaseDSProcessor;
import com.github.multipleds.api.datasource.DSProcessor;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author wang xiao
 * date 2023/6/8
 */
@AutoService(DSProcessor.class)
public class DB2DSProcessor extends BaseDSProcessor {
    @Override
    public String getDatasourceDriver() {
        return "com.ibm.db2.jcc.DB2Driver";
    }

    @Override
    public String getValidateQuery() {
        return "select 1 from sysibm.sysdummy1";
    }

    @Override
    public String getJdbcUrl(BaseConnectParam connectParam) {
        DB2ConnectParam db2Param = (DB2ConnectParam) connectParam;
        String address = String.format("%s%s:%s", "jdbc:db2://", db2Param.getAddress(), db2Param.getPort());
        return String.format("%s/%s", address, db2Param.getDatabase());
    }

    @Override
    public Connection getConnection(BaseConnectParam connectParam) throws ClassNotFoundException, SQLException, IOException {
        DB2ConnectParam db2Param = (DB2ConnectParam) connectParam;
        Class.forName(getDatasourceDriver());
        return DriverManager.getConnection(getJdbcUrl(db2Param), db2Param.getUsername(), db2Param.getPassword());
    }

    @Override
    public DbType getDbType() {
        return DbType.DB2;
    }

    @Override
    public DSProcessor create() {
        return new DB2DSProcessor();
    }
}
