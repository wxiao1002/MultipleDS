package com.github.multipleds.mysql;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.client.BaseDSClient;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class MysqlDSClient extends BaseDSClient {
    public MysqlDSClient(BaseConnectParam baseConnectParam, DbType dbType) {
        super(baseConnectParam, dbType);
    }
}
