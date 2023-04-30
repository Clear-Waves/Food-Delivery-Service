package cdu.cyj.product.service;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 21968
* @description 针对表【product】的数据库操作Service
* @createDate 2023-03-15 12:23:03
*/
public interface ProductService extends IService<Product> {

    List<Product> getProductListByShopId(Long shopId);
}
