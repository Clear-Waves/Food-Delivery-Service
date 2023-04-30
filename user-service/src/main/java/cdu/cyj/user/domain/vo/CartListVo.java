package cdu.cyj.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartListVo {
    /**
     * 购物车id
     */
    private Long id;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 数量
     */
    private Integer quantity;
}
