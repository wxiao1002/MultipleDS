package com.github.multipleds.db2;

import com.github.multipleds.api.DSChannelFactory;
import com.github.multipleds.api.DsChannel;
import com.google.auto.service.AutoService;

/**
 * @author wang xiao
 * date 2023/6/8
 */
@AutoService(DSChannelFactory.class)
public class DB2DSChannelFactory implements DSChannelFactory {
    @Override
    public DsChannel create() {
        return new DB2DSChannel();
    }

    @Override
    public String getLabel() {
        return "db2";
    }
}
