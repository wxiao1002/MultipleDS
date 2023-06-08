package com.github.multipleds.oracle;

import com.github.multipleds.api.BaseConnectParam;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public class OracleConnectParam extends BaseConnectParam {
    protected DbConnectType connectType;

    @Override
    public String toString() {
        return "OracleConnectParam{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", address='" + address + '\''
                + ", database='" + database + '\''
                + ", driverClassName='" + driverClassName + '\''
                + ", validateQuery='" + validateQuery + '\''
                + ", otherParameters='" + otherParameters + '\''
                + '}';
    }

    public DbConnectType getConnectType() {
        return connectType;
    }

    public void setConnectType(DbConnectType connectType) {
        this.connectType = connectType;
    }
}
