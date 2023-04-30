package cdu.cyj.order.service;

import cdu.cyj.common.domain.pojo.Orderitem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 21968
* @description 针对表【orderitem】的数据库操作Service
* @createDate 2023-04-18 21:12:29
*/
public interface OrderitemService extends IService<Orderitem> {

    List<Orderitem> getOrderItemListByOrderId(Long orderId);

}
