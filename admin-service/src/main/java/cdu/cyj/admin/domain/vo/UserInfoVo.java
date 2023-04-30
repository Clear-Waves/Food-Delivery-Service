package cdu.cyj.admin.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoVo {

    /**
     * 用户id
     */
    private Long id;

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
     * 用户权限
     */
    private List<String> permissions;
}
