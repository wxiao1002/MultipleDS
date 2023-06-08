package com.github.multipleds.pgsql;

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
public class PgsqlDSProcessor extends BaseDSProcessor {
    @Override
    public String getDatasourceDriver() {
        return "org.postgresql.Driver";
    }

    @Override
    public String getValidateQuery() {
        return "select version()";
    }

    @Override
    public String getJdbcUrl(BaseConnectParam connectParam) {
        PgsqlConnectParam pgsqlConnectParam = (PgsqlConnectParam) connectParam;
        String address = String.format("%s%s:%s", "jdbc:postgresql://", pgsqlConnectParam.getAddress(),
                pgsqlConnectParam.getPort());
        return String.format("%s/%s", address, pgsqlConnectParam.getDatabase());
    }

    @Override
    public Connection getConnection(BaseConnectParam connectParam) throws ClassNotFoundException, SQLException, IOException {
        PgsqlConnectParam pgsqlConnectParam = (PgsqlConnectParam) connectParam;
        Class.forName(getDatasourceDriver());
        return DriverManager.getConnection(getJdbcUrl(pgsqlConnectParam),
                pgsqlConnectParam.getUsername(),
                pgsqlConnectParam.getPassword());
    }

    @Override
    public DbType getDbType() {
        return DbType.POSTGRESQL;
    }

    @Override
    public DSProcessor create() {
        return new PgsqlDSProcessor();
    }
}
