package com.nowavesnokings.firstwin.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * @description Spu
 * @author  ssx
 * @version V1.0
 * @className Spu
 * @date 2021-01-21 
 * @since 1.8
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "spu")
public class Spu extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  8177515153871551758L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;

	/**
	 * 商品名称
	 */
	@Column(name = "title") 
	private String title;

	/**
	 * 商品主题
	 */
	@Column(name = "sub_title") 
	private String subTitle;

	/**
	 * 商品图片
	 */
	@Column(name = "img") 
	private String img;

	/**
	 * 商品标签
	 */
	@Column(name = "tags") 
	private String tags;

	/**
	 * 商品描述
	 */
	@Column(name = "description") 
	private String description;

	/**
	 * 是否上线
	 */
	@Column(name = "online") 
	private Long online;

	/**
	 * 文本型价格
	 */
	@Column(name = "price") 
	private String price;

	/**
	 * 折扣价格
	 */
	@Column(name = "discout_price") 
	private String discoutPrice;

	/**
	 * 某种规格可以直接附加单品图片
	 */
	@Column(name = "sketch_spec_id") 
	private Long sketchSpecId;

	/**
	 * 默认选中的单品
	 */
	@Column(name = "default_sku_id") 
	private Long defaultSkuId;

	/**
	 * 商品主题图
	 */
	@Column(name = "spu_theme_img") 
	private String spuThemeImg;

	/**
	 * 主题图
	 */
	@Column(name = "for_theme_img") 
	private String forThemeImg;

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

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "spu_id")
	private List<Sku> skus;

}
