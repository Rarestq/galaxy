package com.wuxiu.galaxy.api.common.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * 基于 baomidou BaseMapper 的 BaseDao 接口服务
 *
 * @param <T>
 * @author wuxiu
 */
public interface BaseDao<T extends BaseModel> extends BaseMapper<T> {
    List<Long> selectIdPage(@Param("cm") Map<String, Object> var1);

    List<Long> selectIdPage(RowBounds var1, @Param("cm") Map<String, Object> var2);

}
