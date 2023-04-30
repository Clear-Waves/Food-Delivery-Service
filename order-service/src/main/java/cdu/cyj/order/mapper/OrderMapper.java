package cdu.cyj.order.mapper;

import cdu.cyj.common.domain.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 21968
* @description 针对表【order】的数据库操作Mapper
* @createDate 2023-03-15 12:29:49
* @Entity cdu.cyj.common.domain.pojo.Order
*/
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}




