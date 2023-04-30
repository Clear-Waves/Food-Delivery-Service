package cdu.cyj.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cdu.cyj.common.domain.pojo.Order;
import cdu.cyj.order.service.OrderService;
import cdu.cyj.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21968
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-03-15 12:29:49
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getOrderListByUserId(Long userId) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId, userId);
        return orderMapper.selectList(wrapper);
    }
}




