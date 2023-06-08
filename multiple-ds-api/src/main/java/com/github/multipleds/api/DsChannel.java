package com.github.multipleds.api;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public interface DsChannel {

    /**
     * create DSClient
     * @param connectParam  BaseConnectParam
     * @param dbType DbType
     * @return DSClient
     */
    DSClient createDSClient(BaseConnectParam connectParam,DbType dbType);
}
