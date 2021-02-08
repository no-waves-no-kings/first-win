package com.nowavesnokings.firstwin.pojo.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ssx
 * @version V1.0
 * @className PageCounter
 * @description 分页转化器
 * @date 2021-02-03 11:20
 * @since 1.8
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class PageCounter {
    private Integer page;

    private Integer size;

    public PageCounter(Integer start, Integer count) {
        this.page = start / count;
        this.size = count;
    }
}
