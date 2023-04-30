package cdu.cyj.product.mapper;

import cdu.cyj.common.domain.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 21968
* @description 针对表【product】的数据库操作Mapper
* @createDate 2023-03-15 12:23:03
* @Entity cdu.cyj.common.domain.pojo.Product
*/
@Repository
public interface ProductMapper extends BaseMapper<Product> {
}




