package cdu.cyj.order.service;

import cdu.cyj.common.domain.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 21968
* @description 针对表【order】的数据库操作Service
* @createDate 2023-03-15 12:29:49
*/
public interface OrderService extends IService<Order> {

    List<Order> getOrderListByUserId(Long userId);
}
