package com.github.multipleds.ssh;

import com.github.multipleds.api.DSChannelFactory;
import com.github.multipleds.api.DsChannel;
import com.google.auto.service.AutoService;

/**
 * @author wang xiao
 * date 2023/6/8
 */
@AutoService(DSChannelFactory.class)
public class SSHDSChannelFactory implements DSChannelFactory {

    @Override
    public DsChannel create() {
        return new SSHDSChannel();
    }

    @Override
    public String getLabel() {
        return "ssh";
    }
}
