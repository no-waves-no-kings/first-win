package com.nowavesnokings.firstwin.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className PagingVO
 * @description 分页展示对象
 * @date 2021-02-03 16:51
 * @since 1.8
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class PagingVO<T> {
    private Long total;
    private Integer count;
    private Integer totalPage;
    private Integer page;
    private List<T> items;

    public PagingVO(Page<T> page) {
        initPageParameters(page);
        this.items = page.getContent();
    }

    protected void initPageParameters(Page<T> page) {
        this.total = page.getTotalElements();
        this.count = page.getSize();
        this.page = page.getNumber();
        this.totalPage = page.getTotalPages();
    }
}
