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
 * @description CategoryCoupon
 * @author  ssx
 * @version V1.0
 * @className CategoryCoupon
 * @date 2020-12-30 
 * @since 1.8
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "category_coupon")
public class CategoryCoupon implements Serializable {

	private static final long serialVersionUID =  5011054711862840645L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;

	/**
	 * 分类id
	 */
	@Column(name = "category_id") 
	private Long categoryId;

	/**
	 * 优惠券id
	 */
	@Column(name = "coupon_id") 
	private Long couponId;

}
