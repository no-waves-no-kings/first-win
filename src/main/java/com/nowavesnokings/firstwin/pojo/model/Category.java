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
 * @description Category
 * @author  ssx
 * @version V1.0
 * @className Category
 * @date 2020-12-25 
 * @since 1.8
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  1478593231764771195L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;

	/**
	 * 名称
	 */
	@Column(name = "name") 
	private String name;

	/**
	 * 描述
	 */
	@Column(name = "description") 
	private String description;

	/**
	 * 是否根节点
	 */
	@Column(name = "root_flag") 
	private Boolean rootFlag;

	/**
	 * 是否上线
	 */
	@Column(name = "online") 
	private Boolean online;

	/**
	 * 父分类id
	 */
	@Column(name = "parent_id") 
	private Long parentId;

	/**
	 * 排序
	 */
	@Column(name = "index") 
	private Integer index;

	/**
	 * 分类层级
	 */
	@Column(name = "level") 
	private Integer level;

	/**
	 * 图片
	 */
	@Column(name = "img") 
	private String img;
}
