package com.nowavesnokings.firstwin.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @description Sku
 * @author  ssx
 * @version V1.0
 * @className Sku
 * @date 2021-01-21 
 * @since 1.8
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sku")
@Where(clause = "delete_time is null and online = 1")
public class Sku extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  9099283879120222630L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;

	/**
	 * 商品标题
	 */
	@Column(name = "title") 
	private String title;

	@Column(name = "spu_id")
	private Long spuId;

	/**
	 * 库存
	 */
	@Column(name = "stock") 
	private Integer stock;

	/**
	 * 价格
	 */
	@Column(name = "price") 
	private BigDecimal price;

	/**
	 * 折扣价格
	 */
	@Column(name = "discount_price") 
	private BigDecimal discountPrice;

	/**
	 * 商品图片
	 */
	@Column(name = "img") 
	private String img;

	/**
	 * 是否上线
	 */
	@Column(name = "online") 
	private Boolean online;

	/**
	 * 商品规格
	 */
	@Column(name = "specs") 
	private String specs;

	/**
	 * 商品规格编码
	 */
	@Column(name = "code") 
	private String code;

	/**
	 * 分类id
	 */
	@Column(name = "category_id") 
	private Long categoryId;

	/**
	 * 根分类id
	 */
	@Column(name = "root_category_id") 
	private Long rootCategoryId;

	public BigDecimal getActualPrice() {
		return this.discountPrice != null ? this.discountPrice : this.price;
	}


}
