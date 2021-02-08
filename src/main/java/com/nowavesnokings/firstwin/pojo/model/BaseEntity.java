package com.nowavesnokings.firstwin.pojo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author ssx
 * @version V1.0
 * @className BaseEntity
 * @description 基础entity类
 * @date 2020-12-20 18:00
 * @since 1.8
 */
@Setter
@Getter
@MappedSuperclass
public class BaseEntity {
    /**
     * 创建时间.
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

    /**
     * 修改时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    /**
     * 删除时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deleteTime;
}
