package com.github.multipleds.mysql;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DSClient;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.DsChannel;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class MySqlDSChannel implements DsChannel {

    @Override
    public MysqlDSClient createDSClient(BaseConnectParam connectParam, DbType dbType) {
        return new MysqlDSClient(connectParam, dbType);
    }
}
