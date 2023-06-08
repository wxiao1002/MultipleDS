package com.github.multipleds.api.datasource;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;

import java.text.MessageFormat;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public abstract class BaseDSProcessor implements DSProcessor {


    @Override
    public String getDatasourceUniqueId(BaseConnectParam connectParam, DbType dbType) {
        return MessageFormat.format("{0}@{1}@{2}@{3}", dbType.getLabel(), connectParam.getUsername(), connectParam.getPassword(),connectParam.getAddress());
    }
}
