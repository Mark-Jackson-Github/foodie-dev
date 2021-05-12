package com.imooc.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用于展示商品评价的VO
 * ic.comment_level as commentLevel,
 *             ic.content,
 *             ic.sepc_name as sepcName,
 *             ic.created_time as createdTime,
 *             u.face as userFace,
 *             u.nickname
 * @author Bobo
 */
@Data
public class ItemCommentVO {
    private Integer commentLevel;
    private String content;
    private String specName;
    private Date createdTime;
    private String userFace;
    private String nickname;


}
