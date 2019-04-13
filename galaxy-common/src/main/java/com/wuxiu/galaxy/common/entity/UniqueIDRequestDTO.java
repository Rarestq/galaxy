package com.wuxiu.galaxy.common.entity;

import com.wuxiu.galaxy.common.enums.DateTimeFormStrEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 唯一id生成器对象
 *
 * @author: wuxiu
 * @date: 2019/4/13 15:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UniqueIDRequestDTO extends BaseBizDTO {

    private static final long serialVersionUID = 5300005404515223765L;

    @Builder(builderMethodName = "uidBuilder")
    public UniqueIDRequestDTO(Integer bizType, String prefix, Integer length) {
        super(bizType);
        this.prefix = prefix;
        this.length = length;
    }

    private String prefix;
    private Integer length;
    private DateTimeFormStrEnum dateTimeFormStrEnum;
    private LocalDateTime referenceTime;
}
