package cdu.cyj.rider.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderListVo {

    /**
     * 订单id
     */
    private Long id;

    /**
     * 配送地址
     */
    private String address;

    /**
     * 运费
     */
    private BigDecimal postage;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 用户电话
     */
    private String userTel;

}
