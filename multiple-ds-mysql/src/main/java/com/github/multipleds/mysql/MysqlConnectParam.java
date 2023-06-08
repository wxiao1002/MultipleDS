package com.github.multipleds.mysql;

import com.github.multipleds.api.BaseConnectParam;

/**
 * mysql 连接参数
 * @author wang xiao
 * date 2023/6/8
 */
public class MysqlConnectParam extends BaseConnectParam {

    @Override
    public String toString() {
        return "MySQLConnectionParam{"
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
