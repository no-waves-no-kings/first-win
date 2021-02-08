package com.nowavesnokings.firstwin.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author ssx
 * @version V1.0
 * @className ActivityPureVO
 * @description 活动简化VO对象
 * @date 2020-12-24 9:43
 * @since 1.8
 */
@Setter
@Getter
@ToString
public class ActivityPureVO {
    /**
     * 活动id
     */
    private Long id;

    /**
     * 活动名
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 入口图片
     */
    private String entranceImg;

    /**
     * 描述
     */
    private String remark;

    /**
     * 上线状态
     */
    private Boolean online;

    /**
     * 内部顶图
     */
    private String internalTopImg;

    /**
     * 开始时间
     */
//    @JsonSerialize(using = Date2SecondLongSerializer.class)
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;
}
