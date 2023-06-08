package com.github.multipleds.api;

import java.sql.Connection;

/**
 * 数据源客户端
 * @author wang xiao
 * date 2023/6/8
 */
public interface DSClient extends AutoCloseable {

    /**
     * 检查客户端
     */
    void checkClient();

    /**
     * 关闭
     */
    @Override
    void close();

    /**
     * 获取sql 连接
     * @return Connection
     */
    Connection getSqlConnection();

}
