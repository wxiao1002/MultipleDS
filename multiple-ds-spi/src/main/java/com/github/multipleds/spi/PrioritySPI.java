package com.github.multipleds.spi;

/**
 * @author wang xiao
 * date 2023/6/8
 */
public interface PrioritySPI extends Comparable<Integer>{

    /**
     * 获取标识
     * @return SPIIdentify
     */
    SPIIdentify getIdentify();

    @Override
    default int compareTo(Integer o) {
        return Integer.compare(getIdentify().priority(), o);
    }
}
