package cdu.cyj.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cdu.cyj.common.domain.pojo.Orderitem;
import cdu.cyj.order.service.OrderitemService;
import cdu.cyj.order.mapper.OrderitemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21968
* @description 针对表【orderitem】的数据库操作Service实现
* @createDate 2023-04-18 21:12:29
*/
@Service
public class OrderitemServiceImpl extends ServiceImpl<OrderitemMapper, Orderitem>
    implements OrderitemService{

    @Autowired
    private OrderitemMapper orderitemMapper;

    @Override
    public List<Orderitem> getOrderItemListByOrderId(Long orderId) {

        LambdaQueryWrapper<Orderitem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Orderitem::getOrderId, orderId);
        return orderitemMapper.selectList(wrapper);
    }
}




