package cdu.cyj.merchant.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopListVo {
    /**
     * 店铺id
     */
    private Long id;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 店铺描述
     */
    private String description;

    /**
     * 店铺缩略图
     */
    private String thumbnail;

    /**
     * 店铺招牌图
     */
    private String signboard;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 月销量
     */
    private Integer monthlySale;

    /**
     * 配送费
     */
    private BigDecimal deliveryCost;

    /**
     * 人均消费
     */
    private BigDecimal perCapitaCost;

    /**
     * 线下店地址
     */
    private String address;

    /**
     * 状态：1：正常；0：打样
     */
    private Integer status;
}
