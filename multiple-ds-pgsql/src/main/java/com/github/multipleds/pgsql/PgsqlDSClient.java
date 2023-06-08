package com.github.multipleds.pgsql;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.client.BaseDSClient;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class PgsqlDSClient extends BaseDSClient {

    public PgsqlDSClient(BaseConnectParam baseConnectParam, DbType dbType) {
        super(baseConnectParam, dbType);
    }
}
