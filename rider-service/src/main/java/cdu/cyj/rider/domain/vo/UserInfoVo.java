package cdu.cyj.rider.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     *
     */
    private Long createBy;

    /**
     *
     */
    private Date createTime;
}
