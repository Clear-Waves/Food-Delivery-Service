package cdu.cyj.user.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopListVo {
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
     * 店铺缩略图
     */
    private String thumbnail;

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
}
