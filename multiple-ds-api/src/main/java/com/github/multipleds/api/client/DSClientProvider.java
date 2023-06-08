package com.github.multipleds.api.client;

import com.github.multipleds.api.*;
import com.github.multipleds.api.util.DSUtils;
import org.springframework.util.ConcurrentLruCache;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class DSClientProvider {

    private static final DSClientProvider INSTANCE = new DSClientProvider();

    private DSChannelManager dataSourcePluginManager;

    private Map<String,DSClient> clientCache = new ConcurrentHashMap<>(100);

    private DSClientProvider() {
        initDataSourcePlugin();
    }


    public static DSClientProvider getInstance() {
        return DSClientProvider.INSTANCE;
    }

    public Connection getConnection(DbType dbType, BaseConnectParam connectionParam) throws ExecutionException {
        String uniqueId = DSUtils.getDatasourceUniqueId(connectionParam,dbType);
        DSClient dataSourceClient = clientCache.computeIfAbsent(uniqueId,k->buildDSClient(connectionParam,dbType));
        return dataSourceClient.getSqlConnection();
    }

    private DSClient buildDSClient(BaseConnectParam baseConnectParam,DbType dbType){
        Map<String, DsChannel> dataSourceChannelMap = dataSourcePluginManager.getDataSourceChannelMap();
        DsChannel dataSourceChannel = dataSourceChannelMap.get(dbType.getLabel());
        if (null == dataSourceChannel) {
            throw new RuntimeException(String.format("datasource plugin '%s' is not found", dbType.getLabel()));
        }
        return dataSourceChannel.createDSClient(baseConnectParam, dbType);
    }


    private void initDataSourcePlugin() {
        dataSourcePluginManager = new DSChannelManager();
        dataSourcePluginManager.installPlugin();
    }
}
