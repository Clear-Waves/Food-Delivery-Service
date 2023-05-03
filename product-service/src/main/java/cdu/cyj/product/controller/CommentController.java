package cdu.cyj.product.controller;

import cdu.cyj.common.domain.pojo.Comment;
import cdu.cyj.product.service.CommentService;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /* 查询接口 */
    @GetMapping("/listByCommentIdAndShopId")
    public List<Comment> getListByCommentIdAndShopId(Long commentId, Long shopId) {
        return commentService.listByCommentIdAndShopId(commentId, shopId);
    }

    @GetMapping("/listByCommentId")
    public List<Comment> getListByCommentId(Long commentId) {
        return commentService.listByCommentId(commentId);
    }


    /*  添加接口  */


    @PostMapping
    public Long addComment(@RequestBody Comment comment) {
        if (commentService.save(comment)) {
            return comment.getId();
        } else {
            return null;
        }
    }
}
