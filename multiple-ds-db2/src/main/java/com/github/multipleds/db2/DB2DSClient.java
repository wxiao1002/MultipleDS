package com.github.multipleds.db2;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.client.BaseDSClient;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class DB2DSClient extends BaseDSClient {
    public DB2DSClient(BaseConnectParam baseConnectParam, DbType dbType) {
        super(baseConnectParam, dbType);
    }
}
