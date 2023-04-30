package cdu.cyj.common.domain.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product implements Serializable {
    /**
     * 产品id
     */
    @TableId
    private Long id;

    /**
     * 商店id
     */
    private Long shopId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 产品子图集
     */
    private String subimages;

    /**
     * 产品详情
     */
    private String detail;

    /**
     * 月销量
     */
    private Integer monthlySales;

    /**
     * 产品价格
     */
    private BigDecimal price;

    /**
     * 产品库存
     */
    private Integer stock;

    /**
     * 产品状态：1：正常；0：下架
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