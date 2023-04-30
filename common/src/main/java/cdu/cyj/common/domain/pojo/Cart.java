package cdu.cyj.common.domain.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName cart
 */
@TableName(value ="cart")
@Data
public class Cart implements Serializable {
    /**
     * 购物车id
     */
    @TableId
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 是否勾选：0：未勾选；1；已勾选
     */
    private Integer checked;

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