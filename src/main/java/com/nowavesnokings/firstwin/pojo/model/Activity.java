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
import java.util.Date;
import java.util.List;

/**
 * @description Activity
 * @author  ssx
 * @version V1.0
 * @className Activity
 * @date 2020-12-23 
 * @since 1.8
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "activity")
public class Activity extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  610952090774984479L;
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
	 * 备注
	 */
	@Column(name = "remark") 
	private String remark;

	/**
	 * 入口图片
	 */
	@Column(name = "entrance_img") 
	private String entranceImg;

	/**
	 * 内部顶部图片
	 */
	@Column(name = "internal_top_img") 
	private String internalTopImg;

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
	 * 上线状态
	 */
	@Column(name = "online") 
	private Boolean online;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_id")
	private List<Coupon> coupons;

}
