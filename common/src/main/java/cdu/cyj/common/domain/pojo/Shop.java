package cdu.cyj.common.domain.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName shop
 */
@TableName(value ="shop")
@Data
public class Shop implements Serializable {
    /**
     * 店铺id
     */
    @TableId
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