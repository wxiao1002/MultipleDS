package com.github.multipleds.oracle;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.client.BaseDSClient;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class OracleDSClient extends BaseDSClient {
    public OracleDSClient(BaseConnectParam baseConnectParam, DbType dbType) {
        super(baseConnectParam, dbType);
    }


    @Override
    protected void setDefaultValidateQuery() {
        this.baseConnectParam.setValidateQuery("select 1 from dual");
    }
}
