package com.github.multipleds.api;

import com.github.multipleds.spi.PrioritySPIFactory;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class DSChannelManager {

    private final Map<String, DsChannel> datasourceChannelMap = new ConcurrentHashMap<>();

    public Map<String, DsChannel> getDataSourceChannelMap() {
        return Collections.unmodifiableMap(datasourceChannelMap);
    }

    public void installPlugin() {
        PrioritySPIFactory<DSChannelFactory> prioritySPIFactory =
                new PrioritySPIFactory<>(DSChannelFactory.class);
        for (Map.Entry<String, DSChannelFactory> entry : prioritySPIFactory.getSPIMap().entrySet()) {
            final DSChannelFactory factory = entry.getValue();
            final String name = entry.getKey();
            if (datasourceChannelMap.containsKey(name)) {
                throw new IllegalStateException(format("Duplicate datasource plugins named '%s'", name));
            }
            loadDatasourceClient(factory);
        }
    }

    private void loadDatasourceClient(DSChannelFactory datasourceChannelFactory) {
        DsChannel datasourceChannel = datasourceChannelFactory.create();
        datasourceChannelMap.put(datasourceChannelFactory.getLabel(), datasourceChannel);
    }
}
