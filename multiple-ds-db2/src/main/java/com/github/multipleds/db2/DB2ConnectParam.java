package com.github.multipleds.db2;

import com.github.multipleds.api.BaseConnectParam;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class DB2ConnectParam extends BaseConnectParam {

    @Override
    public String toString() {
        return "DB2ConnectParam{"
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
