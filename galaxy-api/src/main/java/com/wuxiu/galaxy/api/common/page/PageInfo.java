package com.wuxiu.galaxy.api.common.page;

import java.beans.Transient;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 分页对象
 *
 * @author: wuxiu
 * @date: 2019/4/13 16:17
 */
public class PageInfo<T> extends Pagination {

    private static final long serialVersionUID = 8187030951832478996L;

    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();

    /**
     * 查询参数（ 不会传入到 xml 层，这里是 Controller 层与 service 层传递参数预留 ）
     */
    private Map<String, Object> condition;

    public PageInfo() {
        /* 注意，传入翻页参数 */
    }

    public PageInfo(int current, int size) {
        super(current, size);
    }

    public PageInfo(int current, int size, String orderByField) {
        super(current, size);
        this.setOrderByField(orderByField);
    }

    public PageInfo(int current, int size, String orderByField, boolean isAsc) {
        this(current, size, orderByField);
        this.setAsc(isAsc);
    }

    public List<T> getRecords() {
        return records;
    }

    public PageInfo<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Transient
    public Map<String, Object> getCondition() {
        return condition;
    }

    public PageInfo<T> setCondition(Map<String, Object> condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder pg = new StringBuilder();
        pg.append(" PageInfo:{ [").append(super.toString()).append("], ");
        if (records != null) {
            pg.append("records-size:").append(records.size());
        } else {
            pg.append("records is null");
        }
        return pg.append(" }").toString();
    }
}

