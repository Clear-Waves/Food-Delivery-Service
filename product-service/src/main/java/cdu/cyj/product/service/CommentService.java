package cdu.cyj.product.service;

import cdu.cyj.common.domain.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 21968
* @description 针对表【comment】的数据库操作Service
* @createDate 2023-05-02 17:39:14
*/
public interface CommentService extends IService<Comment> {

    List<Comment> listByCommentIdAndShopId(Long commentId, Long shopId);

    List<Comment> listByCommentId(Long commentId);
}
