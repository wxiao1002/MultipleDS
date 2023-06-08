package com.github.multipleds.pgsql;

import com.github.multipleds.api.BaseConnectParam;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class PgsqlConnectParam extends BaseConnectParam {

    @Override
    public String toString() {
        return "PgsqlConnectParam{"
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
