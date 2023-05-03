package cdu.cyj.user.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.user.domain.dto.CommentDto;
import cdu.cyj.user.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/getCommentListByShopId")
    public ResponseResult<?> getCommentListByShopId(Long shopId) {
        return commentService.listByShopId(shopId);
    }



    @PostMapping("/comment")
    public ResponseResult<?> comment(@RequestBody CommentDto commentDto) {
        return commentService.comment(commentDto);
    }
}
