package com.github.multipleds.api;

import com.github.multipleds.spi.PrioritySPI;
import com.github.multipleds.spi.SPIIdentify;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public interface DSChannelFactory extends PrioritySPI {

    /**
     * create a new DS channel
     * @return DsChannel
     */
    DsChannel create();

    /**
     * get component name
     * @return String
     */
    String getLabel();

    @Override
    default SPIIdentify getIdentify() {
        return new SPIIdentify(SPIIdentify.DEFAULT_PRIORITY,getLabel());
    }
}
