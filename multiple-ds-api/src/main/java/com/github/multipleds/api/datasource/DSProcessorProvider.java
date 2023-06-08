package com.github.multipleds.api.datasource;

import com.github.multipleds.api.DbType;

import java.util.Map;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class DSProcessorProvider {

    private static final DSProcessorProvider INSTANCE = new DSProcessorProvider();
    private DSProcessorManager dsProcessorManager;

    private DSProcessorProvider() {
        initDataSourceProcessorPlugin();
    }


    public static DSProcessorProvider getInstance() {
        return INSTANCE;
    }

    public DSProcessor getDataSourceProcessor(DbType dbType) {
        return dsProcessorManager.getDsProcessorMap().get(dbType.name());
    }

    public Map<String, DSProcessor> getDataSourceProcessorMap() {
        return dsProcessorManager.getDsProcessorMap();
    }

    private void initDataSourceProcessorPlugin() {
        this.dsProcessorManager = new DSProcessorManager();
        this.dsProcessorManager.installProcessor();
    }
}
