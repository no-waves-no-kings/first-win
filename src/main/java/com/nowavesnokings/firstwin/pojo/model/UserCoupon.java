package com.nowavesnokings.firstwin.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @description UserCoupon
 * @author  ssx
 * @version V1.0
 * @className UserCoupon
 * @date 2020-12-31 
 * @since 1.8
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_coupon")
public class UserCoupon extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  9196788545520882313L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;

	/**
	 * 优惠券状态;1.未使用,2.已使用,3.已过期
	 */
	@Column(name = "status") 
	private Integer status;

	/**
	 * 订单id
	 */
	@Column(name = "order_id") 
	private Long orderId;

	/**
	 * 用户id
	 */
	@Column(name = "user_id") 
	private Long userId;

	/**
	 * 优惠券id
	 */
	@Column(name = "coupon_id") 
	private Long couponId;

}
