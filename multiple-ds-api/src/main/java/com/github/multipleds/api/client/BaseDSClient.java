package com.github.multipleds.api.client;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DSClient;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.datasource.JDBCDSProvider;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StopWatch;

import java.sql.Connection;
import java.util.Objects;

/**
 * 基础数据源 client
 * @author wang xiao
 * date 2023/6/8
 */
public class BaseDSClient implements DSClient {

    public static final String DEFAULT_USERNAME = "root";

    public static final String DEFAULT_VALIDATE_QUERY   = "select 1";

    protected final BaseConnectParam baseConnectParam;

    protected HikariDataSource dataSource;

    protected JdbcTemplate jdbcTemplate;

    public BaseDSClient(BaseConnectParam baseConnectParam, DbType dbType) {
        this.baseConnectParam = baseConnectParam;
        this.beforeInit();
        this.checkEnv();
        this.initClient(dbType);
        this.checkClient();
    }

    /**
     * 初始化之前的动作
     */
    protected void beforeInit(){
    }

    /**
     * 检查环境
     */
    protected void checkEnv(){
        this.checkValidationQuery();
        this.checkUsername();
    }

    protected void checkValidationQuery() {
        if (Objects.isNull(this.baseConnectParam.getValidateQuery())|| this.baseConnectParam.getValidateQuery().isBlank()) {
            this.baseConnectParam.setValidateQuery(DEFAULT_VALIDATE_QUERY);
        }
    }


    protected void checkUsername() {
        if (Objects.isNull(this.baseConnectParam.getUsername())|| this.baseConnectParam.getAddress().isBlank()) {
            this.baseConnectParam.setUsername(DEFAULT_USERNAME);
        }
    }


    protected void initClient(DbType dbType){
        this.dataSource = JDBCDSProvider.createJDBCDataSource(this.baseConnectParam,dbType);
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public void checkClient(){
      try {
          this.jdbcTemplate.execute(this.baseConnectParam.getValidateQuery());
      }catch (Exception e){
          throw new RuntimeException("JDBC connect failed");
      }
    }

    @Override
    public void close() {
        this.jdbcTemplate = null;
        this.dataSource.close();
    }

    @Override
    public Connection getSqlConnection() {
        try {
            return this.dataSource.getConnection();
        }catch (Exception e){
            return null;
        }
    }
}
