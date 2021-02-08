package com.nowavesnokings.firstwin.pojo.vo;

import com.nowavesnokings.firstwin.util.DozerUtils;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className PageDozerVO
 * @description 分页展示对象
 * @date 2021-02-03 16:38
 * @since 1.8
 */
@ToString
@NoArgsConstructor
public class PagingDozerVO<T,K> extends PagingVO {
    @SuppressWarnings("unchecked")
    public PagingDozerVO(Page<T> page, Class<K> kClass) {
        initPageParameters(page);
        List<T> content = page.getContent();
        List<K> kList = DozerUtils.convertListBean(content, kClass);
        this.setItems(kList);
    }
}
