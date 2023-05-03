package cdu.cyj.user.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommentDto {

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
    private String pictures;

    /**
     *
     */
    private Long orderId;
}
