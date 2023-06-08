package com.github.multipleds.dameng;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.datasource.BaseDSProcessor;
import com.github.multipleds.api.datasource.DSProcessor;
import com.google.auto.service.AutoService;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author wang xiao
 * date 2023/6/8
 */
@AutoService(DSProcessor.class)
public class DamengDSProcessor extends BaseDSProcessor {

    @Override
    public String getDatasourceDriver() {
        return "dm.jdbc.driver.DmDriver";
    }

    @Override
    public String getValidateQuery() {
        return "select 1";
    }

    @Override
    public String getJdbcUrl(BaseConnectParam connectParam) {
        DamengConnectParam damengConnectParam = (DamengConnectParam) connectParam;
        String address = String
                .format("%s%s:%s", "jdbc:dm://", damengConnectParam.getAddress(),
                        damengConnectParam.getPort());
        return StringUtils.isEmpty(damengConnectParam.getDatabase()) ? address
                : String.format("%s/%s", address,
                damengConnectParam.getDatabase());
    }

    @Override
    public Connection getConnection(BaseConnectParam connectParam) throws ClassNotFoundException, SQLException, IOException {
        DamengConnectParam damengConnectParam = (DamengConnectParam) connectParam;
        Class.forName(getDatasourceDriver());
        return DriverManager.getConnection(getJdbcUrl(damengConnectParam), damengConnectParam.getUsername(),
                damengConnectParam.getPassword());
    }

    @Override
    public DbType getDbType() {
        return DbType.DAMENG;
    }

    @Override
    public DSProcessor create() {
        return new DamengDSProcessor();
    }
}
