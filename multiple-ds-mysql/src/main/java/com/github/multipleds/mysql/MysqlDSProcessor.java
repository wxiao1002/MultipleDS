package com.github.multipleds.mysql;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.datasource.BaseDSProcessor;
import com.github.multipleds.api.datasource.DSProcessor;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * @author wang xiao
 * date 2023/6/8
 */
@AutoService(DSProcessor.class)
public class MysqlDSProcessor extends BaseDSProcessor {


    private static final String ALLOW_LOAD_LOCAL_IN_FILE_NAME = "allowLoadLocalInfile";

    private static final String AUTO_DESERIALIZE = "autoDeserialize";

    private static final String ALLOW_LOCAL_IN_FILE_NAME = "allowLocalInfile";

    private static final String ALLOW_URL_IN_LOCAL_IN_FILE_NAME = "allowUrlInLocalInfile";


    private static final String APPEND_PARAMS = "allowLoadLocalInfile=false&autoDeserialize=false&allowLocalInfile=false&allowUrlInLocalInfile=false";

    @Override
    public String getDatasourceDriver() {
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public String getValidateQuery() {
        return "select 1";
    }

    @Override
    public String getJdbcUrl(BaseConnectParam connectParam) {
        MysqlConnectParam mysqlConnectParam = (MysqlConnectParam) connectParam;
        String address = String.format("%s%s:%s", "jdbc:mysql://", mysqlConnectParam.getAddress(),
                mysqlConnectParam.getPort());
        String jdbcUrl = String.format("%s/%s", address, mysqlConnectParam.getDatabase());
        Map<String,String> other = mysqlConnectParam.getOtherParameters();
        if (Objects.nonNull(other) && !other.isEmpty()) {
            return String.format("%s?%s&%s", jdbcUrl, transformOther(other), APPEND_PARAMS);
        }
        return String.format("%s?%s", jdbcUrl, APPEND_PARAMS);
    }





    @Override
    public Connection getConnection(BaseConnectParam connectParam) throws ClassNotFoundException, SQLException, IOException {
        MysqlConnectParam mysqlConnectParam = (MysqlConnectParam) connectParam;
        Class.forName(getDatasourceDriver());
        String username = mysqlConnectParam.getUsername();
        if (username.contains(AUTO_DESERIALIZE)) {
            username = username.replace(AUTO_DESERIALIZE, "");
        }
        String password = mysqlConnectParam.getPassword();
        if (password.contains(AUTO_DESERIALIZE)) {
            password = password.replace(AUTO_DESERIALIZE, "");
        }
        return DriverManager.getConnection(getJdbcUrl(mysqlConnectParam), username, password);
    }

    @Override
    public DbType getDbType() {
        return DbType.MYSQL;
    }

    @Override
    public DSProcessor create() {
        return  new MysqlDSProcessor();
    }

    private String transformOther(Map<String, String> paramMap) {
        Map<String, String> otherMap = new HashMap<>();
        paramMap.forEach((k, v) -> {
            if (!checkKeyIsLegitimate(k)) {
                return;
            }
            otherMap.put(k, v);
        });
        List<String> otherList = new ArrayList<>();
        otherMap.forEach((key, value) -> otherList.add(String.format("%s=%s", key, value)));
        return String.join("&", otherList);
    }

    private static boolean checkKeyIsLegitimate(String key) {
        return !key.contains(ALLOW_LOAD_LOCAL_IN_FILE_NAME)
                && !key.contains(AUTO_DESERIALIZE)
                && !key.contains(ALLOW_LOCAL_IN_FILE_NAME)
                && !key.contains(ALLOW_URL_IN_LOCAL_IN_FILE_NAME);
    }
}
