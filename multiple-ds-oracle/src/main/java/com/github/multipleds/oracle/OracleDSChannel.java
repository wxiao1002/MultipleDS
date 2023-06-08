package com.github.multipleds.oracle;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DSClient;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.DsChannel;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class OracleDSChannel implements DsChannel {
    @Override
    public DSClient createDSClient(BaseConnectParam connectParam, DbType dbType) {
        return new OracleDSClient(connectParam, dbType);
    }
}
