package com.github.multipleds.api.datasource;

import java.util.Collections;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class DSProcessorManager {

    private static final Map<String, DSProcessor> DS_PROCESSOR_MAP = new ConcurrentHashMap<>();

    public Map<String, DSProcessor> getDsProcessorMap() {
        return Collections.unmodifiableMap(DS_PROCESSOR_MAP);
    }

    public void installProcessor() {
        for (DSProcessor dsProcessor : ServiceLoader.load(DSProcessor.class)){
            String name = dsProcessor.getDbType().name();
            if (DS_PROCESSOR_MAP.containsKey(name)) {
                throw new IllegalStateException(format("Duplicate datasource plugins named '%s'", name));
            }
            loadDatasourceClient(dsProcessor);
        }
    }

    private void loadDatasourceClient(DSProcessor processor) {
        DSProcessor instance = processor.create();
        DS_PROCESSOR_MAP.put(processor.getDbType().name(), instance);
    }
}
