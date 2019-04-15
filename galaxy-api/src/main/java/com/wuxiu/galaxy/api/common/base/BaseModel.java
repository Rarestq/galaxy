package com.wuxiu.galaxy.api.common.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * MP基础类
 *
 * @author: wuxiu
 * @date: 2019/4/2 14:26
 */


@JsonIgnoreProperties(
        ignoreUnknown = true
)
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = -3467201945298812562L;

    public BaseModel() {
    }
}

