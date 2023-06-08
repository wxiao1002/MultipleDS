package com.github.multipleds.spi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * SPI 工厂
 * <b>加载时候先看label 如果重复则看 priority </b>
 * <b>如果 priority 相同则抛出一场，newSPI 的priority 大于old 就覆盖</b>
 * @author wang xiao
 * date 2023/6/8
 */
public class PrioritySPIFactory <T extends PrioritySPI>{
    private final Map<String,T> map = new HashMap<>();

    public PrioritySPIFactory(Class<T> spiClass){
        for (T t: ServiceLoader.load(spiClass)){
            String label = t.getIdentify().label();
            if (map.containsKey(label)){
                resolveConflict(label,t);
            }else {
                map.put(label,t);
            }
        }
    }

    private void resolveConflict(String label,T newSPI) {
        T oldSPI = map.get(label);
        if (newSPI.compareTo(oldSPI.getIdentify().priority()) == 0) {
            throw new IllegalArgumentException(
                    String.format("These two spi has conflict identify name with the same priority: %s, %s",
                            oldSPI.getIdentify().label(), newSPI.getIdentify().priority()));
        } else if (newSPI.compareTo(oldSPI.getIdentify().priority()) > 0) {
            map.put(label, newSPI);
        }
    }

    public Map<String, T> getSPIMap() {
        return Collections.unmodifiableMap(map);
    }
}
