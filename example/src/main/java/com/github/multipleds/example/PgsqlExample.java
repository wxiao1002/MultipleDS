package com.github.multipleds.example;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.client.DSClientProvider;
import com.github.multipleds.api.util.DSUtils;
import com.github.multipleds.pgsql.PgsqlConnectParam;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class PgsqlExample {

    public static void main(String[] args) {
        var tableInfo = getTableByDSUtils();
        for (String s : tableInfo) {
            System.out.println(s);
        }

        // 也可以使用
     //  DSClientProvider.getInstance().getConnection()
    }

    private static final String TABLE = "TABLE";
    private static final String VIEW = "VIEW";
    private static final String[] TABLE_TYPES = new String[]{TABLE, VIEW};

    public static List<String> getTableByDSUtils(){
        List<String> tableList = null;
        PgsqlConnectParam connectionParam =new PgsqlConnectParam();
        connectionParam.setAddress("123.249.117.198");
        connectionParam.setPort(5432);
        connectionParam.setDatabase("things_link");
        connectionParam.setUsername("xxx");
        connectionParam.setPassword("xxx@1xxx23!");
        connectionParam.setDriverClassName("org.postgresql.Driver");
        Connection connection =
                DSUtils.getConnection(DbType.POSTGRESQL, connectionParam);
        ResultSet tables = null;

        try {
            if (null == connection) {
                System.out.println("connection is null");
            }
            DatabaseMetaData metaData = connection.getMetaData();
            String schema = null;
            try {
                schema = metaData.getConnection().getSchema();
            } catch (SQLException e) {
                System.out.println("connection getSchema is error");
            }
            tables = metaData.getTables(
                    connectionParam.getDatabase(),
                    getDbSchemaPattern(DbType.POSTGRESQL, schema, connectionParam),
                    "%", TABLE_TYPES);
            if (null == tables) {
                System.out.println("connection table  is empty");
            }
            tableList = new ArrayList<>();
            while (tables.next()) {
                String name = tables.getString("TABLE_NAME");
                tableList.add(name);
            }

        } catch (Exception e) {

        }
        return tableList;
    }


    private static String getDbSchemaPattern(DbType dbType, String schema, BaseConnectParam connectionParam) {
        if (dbType == null) {
            return null;
        }
        String schemaPattern = null;
        switch (dbType) {
            case ORACLE:
                schemaPattern = connectionParam.getUsername();
                if (null != schemaPattern) {
                    schemaPattern = schemaPattern.toUpperCase();
                }
                break;

            default:
                break;
        }
        return schemaPattern;
    }
}
