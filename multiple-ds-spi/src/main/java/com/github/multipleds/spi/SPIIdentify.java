package com.github.multipleds.spi;

/**
 * SPI 标识
 * @author wang xiao
 * date 2023/6/8
 */
public record SPIIdentify(int priority,String label) {

    // 默认优先级
    public static final int DEFAULT_PRIORITY = 0;
}
