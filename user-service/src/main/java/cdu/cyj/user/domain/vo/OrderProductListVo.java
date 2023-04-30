package cdu.cyj.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductListVo {

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 数量
     */
    private Integer quantity;
}
