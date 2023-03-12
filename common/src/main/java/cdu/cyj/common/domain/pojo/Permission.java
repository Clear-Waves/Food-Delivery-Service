package cdu.cyj.common.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName permission
 */
@TableName(value ="permission")
@Data
public class Permission implements Serializable {
    /**
     * 权限id
     */
    @TableId
    private Long id;

    /**
     * 权限详情
     */
    private String perm;

    /**
     * 权限状态：1：启用，2：停用
     */
    private Integer status;

    /**
     * 
     */
    private Long createBy;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Long updateBy;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}