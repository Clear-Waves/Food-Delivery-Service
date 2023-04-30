package cdu.cyj.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDetailVo {

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
     * 线下店地址
     */
    private String address;
}
