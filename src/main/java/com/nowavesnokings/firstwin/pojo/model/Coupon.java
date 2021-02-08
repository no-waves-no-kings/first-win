package com.nowavesnokings.firstwin.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description Coupon
 * @author  ssx
 * @version V1.0
 * @className Coupon
 * @date 2020-12-24 
 * @since 1.8
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "coupon")
public class Coupon extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  3009042848405936893L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;

	/**
	 * 活动id
	 */
	@Column(name = "activity_id")
	private Long activityId;

	/**
	 * 标题
	 */
	@Column(name = "title") 
	private String title;

	/**
	 * 描述
	 */
	@Column(name = "description") 
	private String description;

	/**
	 * 折扣率
	 */
	@Column(name = "rate") 
	private BigDecimal rate;

	/**
	 * 满金额
	 */
	@Column(name = "full_money") 
	private BigDecimal fullMoney;

	/**
	 * 开始时间
	 */
	@Column(name = "start_time") 
	private Date startTime;

	/**
	 * 结束时间
	 */
	@Column(name = "end_time") 
	private Date endTime;

	/**
	 * 减金额
	 */
	@Column(name = "minus") 
	private BigDecimal minus;

	/**
	 * 是否全场优惠券
	 */
	@Column(name = "whole_store") 
	private Boolean wholeStore;

	/**
	 * 优惠券类型:1.满减券,2.满金额折扣券,3.无门槛券,4.折扣券
	 */
	@Column(name = "type") 
	private Integer type;

	/**
	 * 优惠券介绍
	 */
	@Column(name = "remark") 
	private String remark;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_id")
	private List<CategoryCoupon> categoryCoupons;
}
