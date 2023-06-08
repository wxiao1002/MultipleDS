package com.github.multipleds.oracle;

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
public class OracleDSProcessor extends BaseDSProcessor {

    @Override
    public String getDatasourceDriver() {
        return "oracle.jdbc.OracleDriver";
    }

    @Override
    public String getValidateQuery() {
        return "select 1 from dual";
    }

    @Override
    public String getJdbcUrl(BaseConnectParam connectParam) {
        OracleConnectParam oracleConnectParam = (OracleConnectParam) connectParam;
        String address;
        String jdbcUrl;
        if (DbConnectType.ORACLE_SID.equals(oracleConnectParam.getConnectType())) {
            address = String.format("%s%s:%s",
                    "jdbc:oracle:thin:@", oracleConnectParam.getAddress(), oracleConnectParam.getPort());
            jdbcUrl = address + ":" + oracleConnectParam.getDatabase();
        } else {
            address = String.format("%s%s:%s",
                    "jdbc:oracle:thin:@//", oracleConnectParam.getAddress(), oracleConnectParam.getPort());
            jdbcUrl = address + "/" + oracleConnectParam.getDatabase();
        }
        return jdbcUrl;

    }

    @Override
    public Connection getConnection(BaseConnectParam connectParam) throws ClassNotFoundException, SQLException, IOException {
        OracleConnectParam oracleConnectParam = (OracleConnectParam) connectParam;
        Class.forName(getDatasourceDriver());
        return DriverManager.getConnection(getJdbcUrl(oracleConnectParam), oracleConnectParam.getUsername(),
                oracleConnectParam.getPassword());
    }

    @Override
    public DbType getDbType() {
        return DbType.POSTGRESQL;
    }

    @Override
    public DSProcessor create() {
        return new OracleDSProcessor();
    }
}
