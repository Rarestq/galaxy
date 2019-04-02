package com.wuxiu.galaxy.common.entity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.beans.ConstructorProperties;
import java.io.Serializable;

@ApiModel(
        description = "响应结果"
)
public class APIResult<T> implements Serializable {

    @ApiModelProperty("调用是否成功")
    @JSONField(
            ordinal = 1
    )
    private boolean success;
    @ApiModelProperty(
            value = "状态码",
            required = true
    )
    @JSONField(
            ordinal = 2
    )
    private String code;
    @ApiModelProperty("调用结果消息")
    @JSONField(
            ordinal = 3
    )
    private String message;
    @ApiModelProperty("成功时响应数据")
    @JSONField(
            ordinal = 4
    )
    private T data;
    @ApiModelProperty(
            value = "时间戳",
            required = true,
            dataType = "Long"
    )
    @JSONField(
            ordinal = 5
    )
    private Long timestamp;

    public static <T> APIResult<T> ok() {
        return (APIResult<T>) ok((Object) null);
    }

    public static <T> APIResult<T> ok(T data) {
        return builder().data(data).build();
    }

    public static <T> APIResult<T> error(String code, String message) {
        return builder().success(false).code(code).message(message).data((Object) null).build();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    private static boolean $default$success() {
        return true;
    }

    private static String $default$code() {
        return "200";
    }

    private static String $default$message() {
        return "";
    }

    private static Long $default$timestamp() {
        return System.currentTimeMillis();
    }

    public static <T> APIResult.APIResultBuilder builder() {
        return new APIResult.APIResultBuilder();
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public APIResult() {
    }

    @ConstructorProperties({"success", "code", "message", "data", "timestamp"})
    public APIResult(boolean success, String code, String message, T data, Long timestamp) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
    }

    public static class APIResultBuilder<T> {

        private boolean success$set;
        private boolean success;
        private boolean code$set;
        private String code;
        private boolean message$set;
        private String message;
        private T data;
        private boolean timestamp$set;
        private Long timestamp;

        APIResultBuilder() {
        }

        public APIResult.APIResultBuilder<T> success(boolean success) {
            this.success = success;
            this.success$set = true;
            return this;
        }

        public APIResult.APIResultBuilder<T> code(String code) {
            this.code = code;
            this.code$set = true;
            return this;
        }

        public APIResult.APIResultBuilder<T> message(String message) {
            this.message = message;
            this.message$set = true;
            return this;
        }

        public APIResult.APIResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public APIResult.APIResultBuilder<T> timestamp(Long timestamp) {
            this.timestamp = timestamp;
            this.timestamp$set = true;
            return this;
        }

        public APIResult<T> build() {
            return new APIResult<T> (this.success$set ? this.success : APIResult.$default$success(), this.code$set ? this.code : APIResult.$default$code(), this.message$set ? this.message : APIResult.$default$message(), this.data, this.timestamp$set ? this.timestamp : APIResult.$default$timestamp());
        }

        @Override
        public String toString() {
            return "APIResult.APIResultBuilder(success=" + this.success + ", code=" + this.code + ", message=" + this.message + ", data=" + this.data + ", timestamp=" + this.timestamp + ")";
        }
    }
}
