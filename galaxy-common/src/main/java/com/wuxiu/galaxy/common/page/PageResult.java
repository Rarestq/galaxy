package com.shi.lissandra.common.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @Package com.mhc.pandora.api.dto.query
 * @Author: Wuer（wuer@maihaoche.com）
 * @Date: 2018/12/21 3:32 PM
 * @Copyright: 2017-2020 www.maihaoche.com Inc. All rights reserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目
 * @Description:
 */

@Data
@JsonInclude(value = JsonInclude.Include.ALWAYS)
public class PageResult<T> implements Serializable {

    private List<T> pageData;

    //当前页
    private Integer pageCurrent = 1;

    //总页数
    private Integer totalCount = 0;

    //当前页记录数
    private Integer pageSize = 2;

    public PageResult() {

    }

    public int getTotalPage() {
        if (pageCurrent < 1) {
            pageCurrent = 1;
        }
        return totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
    }

    public PageResult(Integer size, Integer no, Integer count) {
        pageSize = size;
        pageCurrent = no;
        totalCount = count;
        pageData = new LinkedList<T>();
    }

    public PageResult(Integer size, Integer no, Integer count, List<T> tList) {
        pageSize = size;
        pageCurrent = no;
        totalCount = count;
        pageData = tList;
    }


}
