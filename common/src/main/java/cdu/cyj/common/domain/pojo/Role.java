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
 * @TableName role
 */
@TableName(value ="role")
@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    @TableId
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态：1：可用；2：停用
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