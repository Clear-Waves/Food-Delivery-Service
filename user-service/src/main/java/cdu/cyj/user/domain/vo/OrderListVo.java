package cdu.cyj.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListVo {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 商铺名称
     */
    private String shopName;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 付款金额
     */
    private BigDecimal payment;

    /**
     * 状态：-1：订单关闭；0：未支付；1：已支付；2：商家已接单；3：配送中；4；送达；5：订单完成
     */
    private String status;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 订单商铺详情
     */
    private List<OrderProductListVo> productList;
}
