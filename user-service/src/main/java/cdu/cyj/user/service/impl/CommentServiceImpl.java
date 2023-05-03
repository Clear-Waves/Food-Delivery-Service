package cdu.cyj.user.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.Comment;
import cdu.cyj.common.domain.pojo.Order;
import cdu.cyj.common.domain.pojo.User;
import cdu.cyj.common.enums.AppHttpCodeEnum;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.openfeign.clients.AccountServiceClient;
import cdu.cyj.openfeign.clients.OrderServiceClient;
import cdu.cyj.openfeign.clients.ProductServiceClient;
import cdu.cyj.user.domain.dto.CommentDto;
import cdu.cyj.user.domain.vo.CommentListVo;
import cdu.cyj.user.domain.vo.CommentUserInfoVo;
import cdu.cyj.user.service.CommentService;
import cdu.cyj.user.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Override
    public ResponseResult<?> comment(CommentDto commentDto) {
        // 判断订单所属
        Long userId = SecurityUtil.getUserId();
        Order order = orderServiceClient.getOrderById(commentDto.getOrderId());
        if (!Objects.equals(order.getUserId(), userId)) {
            return ResponseResult.errorResult(500, "订单所属错误");
        }

        // 判断订单状态
        if (order.getStatus() != 5) {
            return ResponseResult.errorResult(500, "不允许评论未完成订单");
        }

        // 封装添加评论
        Comment comment = BeanCopyUtils.copyBean(commentDto, Comment.class);
        comment.setShopId(order.getShopId());
        comment.setUserId(userId);
        comment.setCreateBy(userId);
        comment.setUpdateBy(userId);
        if (Objects.isNull(productServiceClient.addComment(comment))) {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }

        // 修改订单状态
        Order orderSave = new Order();
        orderSave.setId(commentDto.getOrderId());
        orderSave.setStatus(6);
        orderSave.setUpdateBy(userId);
        orderServiceClient.updateOrder(orderSave);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<?> listByShopId(Long shopId) {
        // 查询所有根评论
        List<Comment> commentList = productServiceClient.getCommentListByCommentIdAndShopId(0L, shopId);
        // 根据根评论查询，用户信息，子评论
        List<CommentListVo> commentListVoList = commentList.stream()
                .map(commentItem -> {
                    // 查询子评论
                    List<Comment> subCommentList = productServiceClient.getCommentListByCommentId(commentItem.getId());
                    List<CommentListVo> subCommentVo = BeanCopyUtils.copyBeanList(subCommentList, CommentListVo.class);
                    CommentListVo commentListVo = BeanCopyUtils.copyBean(commentItem, CommentListVo.class);
                    commentListVo.setSubCommentList(subCommentVo);
                    // 查询用户信息
                    Long userId = commentItem.getUserId();
                    User user = accountServiceClient.getUserById(userId);
                    CommentUserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, CommentUserInfoVo.class);
                    commentListVo.setUserInfo(userInfoVo);
                    // 处理图片
                    String[] pictures = commentItem.getPictures().split(",");
                    commentListVo.setPictures(Arrays.asList(pictures));
                    return commentListVo;
                }).collect(Collectors.toList());
        return ResponseResult.okResult(commentListVoList);
    }
}
