package com.wuxiu.galaxy.common.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
