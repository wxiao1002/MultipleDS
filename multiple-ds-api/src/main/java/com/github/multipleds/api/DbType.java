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

    /**
     * mysql
     */
    MYSQL(0, "mysql"),

    /**
     * postgresql
     */
    POSTGRESQL(1, "postgresql"),

    /**
     * dameng
     */
    DAMENG(2, "dameng"),
    /**
     * ORACLE
     */
    ORACLE(3, "oracle"),

    /**
     * DB2
     */
    DB2(4, "db2"),

    /**
     * ssh
     */
    SSH(5, "ssh"),
    ;


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
