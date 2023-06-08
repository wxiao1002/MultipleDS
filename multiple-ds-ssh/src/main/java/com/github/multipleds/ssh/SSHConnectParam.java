package com.github.multipleds.ssh;

import com.github.multipleds.api.BaseConnectParam;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class SSHConnectParam extends BaseConnectParam {

    protected String publicKey;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public String toString() {
        return "SSHConnectParam{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", address='" + address + '\''
                + ", publicKey='" + publicKey + '\'';

    }
}
