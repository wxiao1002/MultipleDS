package com.github.multipleds.ssh;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.client.BaseDSClient;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class SSHDSClient extends BaseDSClient {

    public SSHDSClient(BaseConnectParam baseConnectParam, DbType dbType) {
        super(baseConnectParam, dbType);
    }
}
