package com.wuxiu.galaxy.api.dto;


import com.wuxiu.galaxy.api.common.enums.UserTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 操作人
 *
 * @author: wuxiu
 * @date: 2019/4/15 15:46
 */
@Data
public class OperateUserDTO implements Serializable {
    private static final long serialVersionUID = 538962866950723918L;
    /**
     * 操作人Id
     */
    @NotNull(message = "操作人ID参数不能为空")
    private Long operateUserId;

    /**
     * 操作人名称
     */
    @NotNull(message = "操作人名称参数不能为空")
    private String name;

    /**
     * 用户类型
     *
     */
    @NotNull(message = "用户类型参数不能为空")
    private UserTypeEnum userTypeEnum;

    public static OperateUserDTO ofEmpty(){
        OperateUserDTO operateUserDTO = new OperateUserDTO();
        operateUserDTO.setOperateUserId(0L);
        operateUserDTO.setName("");
        operateUserDTO.setUserTypeEnum(UserTypeEnum.ADMIN);

        return operateUserDTO;
    }
}
