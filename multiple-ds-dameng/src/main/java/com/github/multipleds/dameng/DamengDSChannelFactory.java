package com.github.multipleds.dameng;

import com.github.multipleds.api.DSChannelFactory;
import com.github.multipleds.api.DsChannel;
import com.google.auto.service.AutoService;

/**
 * @author wang xiao
 * date 2023/6/8
 */
@AutoService(DSChannelFactory.class)
public class DamengDSChannelFactory implements DSChannelFactory{
    @Override
    public DsChannel create() {
        return new DamengDSChannel();
    }

    @Override
    public String getLabel() {
        return "dameng";
    }
}
