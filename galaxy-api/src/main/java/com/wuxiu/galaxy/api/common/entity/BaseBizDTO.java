package com.wuxiu.galaxy.api.common.entity;

import com.wuxiu.galaxy.api.common.enums.BizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基础业务对象
 *
 * @author: wuxiu
 * @date: 2019/4/13 15:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseBizDTO implements Serializable {

    private static final long serialVersionUID = -5302911352268847457L;

    /**
     * 归属业务线
     * @see BizTypeEnum
     */
    private Integer bizType;
}
