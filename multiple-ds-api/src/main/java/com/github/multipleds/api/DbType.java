package com.github.multipleds.api;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 数据源类型
 * @author wang xiao
 * date 2023/6/8
 */
public enum DbType {

    MYSQL(0, "mysql"),
    POSTGRESQL(1, "postgresql"),
    HIVE(2, "hive"),
    SPARK(3, "spark"),
    CLICKHOUSE(4, "clickhouse"),
    ORACLE(5, "oracle"),
    SQLSERVER(6, "sqlserver"),
    DB2(7, "db2"),
    PRESTO(8, "presto"),
    H2(9, "h2"),
    REDSHIFT(10, "redshift"),
    ATHENA(11, "athena"),

    OCEAN_BASE(12, "oceanBase");


    private final int code;

    private final String label;

    DbType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    private static final Map<Integer,DbType> DB_TYPE_MAP =
            Arrays.stream(DbType.values()).collect(Collectors.toMap(DbType::getCode, Function.identity()));
    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static DbType of(int type){
        if (DB_TYPE_MAP.containsKey(type)) {
            return DB_TYPE_MAP.get(type);
        }
        return null;
    }

    public static DbType ofName(String name) {
        return Arrays.stream(DbType.values()).filter(e -> e.name().equals(name)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("no such db type"));
    }
}
