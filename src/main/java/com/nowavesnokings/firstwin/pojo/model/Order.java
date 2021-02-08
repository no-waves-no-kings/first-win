package com.nowavesnokings.firstwin.pojo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nowavesnokings.firstwin.core.enumeration.OrderStatusType;
import com.nowavesnokings.firstwin.pojo.dto.OrderAddressDTO;
import com.nowavesnokings.firstwin.util.CommonUtils;
import com.nowavesnokings.firstwin.util.GenericAndJson;
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
import java.util.Date;
import java.util.List;


/**
 * @description Order
 * @author  ssx
 * @version V1.0
 * @className Order
 * @date 2021-01-17 
 * @since 1.8
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "`order`")
@Where(clause = "delete_time is null")
public class Order extends BaseEntity implements Serializable {

	private static final long serialVersionUID =  724484286975540778L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;

	/**
	 * 订单号
	 */
	@Column(name = "order_no") 
	private String orderNo;

	/**
	 * 用户id
	 */
	@Column(name = "user_id") 
	private Long userId;

	/**
	 * 原价格
	 */
	@Column(name = "total_price") 
	private BigDecimal totalPrice;

	/**
	 * 商品数量
	 */
	@Column(name = "total_count") 
	private Integer totalCount;

	/**
	 * 总价格
	 */
	@Column(name = "final_total_price") 
	private BigDecimal finalTotalPrice;

	/**
	 * 邮费
	 */
	@Column(name = "postage") 
	private BigDecimal postage;

	/**
	 * 优惠券id
	 */
	@Column(name = "coupon_id") 
	private Long couponId;

	/**
	 * 快照主题
	 */
	@Column(name = "snap_title") 
	private String snapTitle;

	/**
	 * 快照图片
	 */
	@Column(name = "snap_img") 
	private String snapImg;

	/**
	 * 快照sku信息
	 */
	@Column(name = "snap_items") 
	private String snapItems;

	/**
	 * 快照地址信息
	 */
	@Column(name = "snap_address") 
	private String snapAddress;

	/**
	 * 支付id
	 */
	@Column(name = "prepay_id") 
	private String prepayId;

	/**
	 * 订单状态:1.未支付;2.待发货;3.已取消;4.已完成
	 */
	@Column(name = "status") 
	private Integer status;

	@Column(name = "placed_time")
	private Date placedTime;

	@Column(name = "expired_time")
	private Date expiredTime;

	public void setSnapItems(List<OrderSku> orderSkus) {
		this.snapItems = GenericAndJson.objectToJson(orderSkus);
	}

	public List<OrderSku> getSnapItems() {
		return GenericAndJson.jsonToObject(this.snapItems, new TypeReference<List<OrderSku>>() {
		});
	}

	public void setSnapAddress(OrderAddressDTO orderAddressDTO) {
		this.snapAddress = GenericAndJson.objectToJson(orderAddressDTO);
	}

	public OrderAddressDTO getSnapAddress() {
		return GenericAndJson.jsonToObject(this.snapAddress, new TypeReference<OrderAddressDTO>() {
		});
	}

	public Boolean canCancel() {
		OrderStatusType orderStatusType = OrderStatusType.getOrderStatusTypeByCode(this.status);
		if (!OrderStatusType.UN_PAID.equals(orderStatusType)) {
			return Boolean.TRUE;
		}
		return CommonUtils.isTimeExpired(new Date(), this.expiredTime);
	}

}
