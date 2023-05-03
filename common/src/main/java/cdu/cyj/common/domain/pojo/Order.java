package cdu.cyj.common.domain.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName order
 */
@TableName(value ="table_order")
@Data
public class Order implements Serializable {
    /**
     * 订单id
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 骑手id
     */
    private Long riderId;

    /**
     * 付款金额
     */
    private BigDecimal payment;

    /**
     * 付款方式：1：银行卡；2：支付宝；3：微信
     */
    private Integer paymentType;

    /**
     * 配送地址
     */
    private String address;

    /**
     * 运费
     */
    private BigDecimal postage;


    /**
     * 订单备注
     */
    private String remark;

    /**
     * 状态：-1：订单关闭；0：未支付；1：已支付；2：商家已接单；3：配送中；4；送达；5：订单完成
     */
    private Integer status;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     *
     */
    private Long createBy;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     *
     */
    private Long updateBy;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     *
     */
    @TableLogic
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}