package com.github.multipleds.dameng;

import com.github.multipleds.api.BaseConnectParam;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class DamengConnectParam extends BaseConnectParam {

    @Override
    public String toString() {
        return "DamengConnectParam{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", address='" + address + '\''
                + ", database='" + database + '\''
                + ", driverClassName='" + driverClassName + '\''
                + ", validateQuery='" + validateQuery + '\''
                + ", otherParameters='" + otherParameters + '\''
                + '}';
    }
}
