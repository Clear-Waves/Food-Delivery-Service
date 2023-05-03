package cdu.cyj.user.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CommentListVo {
    /**
     * 评论id
     */
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 评论图片
     */
    private List<String> pictures;

    /**
     * 评论用户
     */
    private CommentUserInfoVo userInfo;

    /**
     * 子评论列表
     */
    private List<CommentListVo> subCommentList;

    /**
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
