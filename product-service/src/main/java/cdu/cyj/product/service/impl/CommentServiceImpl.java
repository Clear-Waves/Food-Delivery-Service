package cdu.cyj.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cdu.cyj.common.domain.pojo.Comment;
import cdu.cyj.product.service.CommentService;
import cdu.cyj.product.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21968
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-05-02 17:39:14
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listByCommentIdAndShopId(Long commentId, Long shopId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getCommentId, commentId).eq(Comment::getShopId, shopId);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public List<Comment> listByCommentId(Long commentId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getCommentId, commentId);
        return commentMapper.selectList(wrapper);
    }
}




