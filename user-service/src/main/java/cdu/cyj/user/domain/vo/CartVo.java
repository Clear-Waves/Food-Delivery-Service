package cdu.cyj.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVo {

    private BigDecimal totalPrice;

    private List<CartListVo> cartList;
}
