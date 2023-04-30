package cdu.cyj.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListVo {

    /**
     * 产品id
     */
    private Long id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 缩略图
     */
    private String thumbnail;

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
}
