package com.wuxiu.galaxy.service.core.biz.mq.producer;

import com.wuxiu.galaxy.api.common.expection.MQException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;

/**
 * RocketMQ的生产者的抽象基类
 *
 * @author: wuxiu
 * @date: 2019/4/14 21:18
 */
@Slf4j
public abstract class AbstractMQProducer {

    /**
     * 默认的producer级的topic
     * 为了向前兼容
     */
    private String defaultTopic;
    /**
     * 默认的producer级的tag
     * 为了向前兼容
     */
    private String defaultTag;

    public void setDefaultTopic(String defaultTopic) {
        this.defaultTopic = defaultTopic;
    }

    public void setDefaultTag(String defaultTag) {
        this.defaultTag = defaultTag;
    }

    private static MessageQueueSelector messageQueueSelector = new SelectMessageQueueByHash();

    public AbstractMQProducer() {
    }

    private DefaultMQProducer producer;

    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    private Message buildMessage(Object message) {
        return MessageBuilder.of(message).topic(defaultTopic).tag(defaultTag).build();
    }

    /**
     * 同步发送消息
     *
     * @param message 消息体
     * @throws MQException 消息异常
     */
    public void syncSend(Message message) throws MQException {
        try {
            SendResult sendResult = producer.send(message);
            log.debug("send rocketmq message ,messageId : {}", sendResult.getMsgId());
            this.doAfterSyncSend(message, sendResult);
        } catch (Exception e) {
            log.error("消息发送失败，topic : {}, msgObj : {}, e:{}", message.getTopic(), message, e);
            throw new MQException("消息发送失败，topic :" + message.getTopic() + ",e:" + e.getMessage());
        }
    }

    /**
     * 同步发送消息，兼容老版本
     *
     * @param obj raw message object
     * @throws MQException 消息异常
     * @deprecated please use syncSend(Message message)
     */
    public void syncSend(Object obj) throws MQException {
        Message message = buildMessage(obj);
        try {
            SendResult sendResult = producer.send(message);
            log.debug("send rocketmq message ,messageId : {}", sendResult.getMsgId());
            this.doAfterSyncSend(message, sendResult);
        } catch (Exception e) {
            log.error("消息发送失败，topic : {}, msgObj : {}, e : {}", message.getTopic(), message, e);
            throw new MQException("消息发送失败，topic :" + message.getTopic() + ",e:" + e.getMessage());
        }
    }


    /**
     * 同步发送消息
     *
     * @param message 消息体
     * @param hashKey 用于hash后选择queue的key
     * @throws MQException 消息异常
     */
    public void syncSendOrderly(Message message, String hashKey) throws MQException {
        if (StringUtils.isEmpty(hashKey)) {
            // fall back to normal
            syncSend(message);
        }
        try {
            SendResult sendResult = producer.send(message, messageQueueSelector, hashKey);
            log.debug("send rocketmq message orderly ,messageId : {}", sendResult.getMsgId());
            this.doAfterSyncSend(message, sendResult);
        } catch (Exception e) {
            log.error("顺序消息发送失败，topic : {}, msgObj : {}, e: {}", message.getTopic(), message, e);
            throw new MQException("顺序消息发送失败，topic :" + message.getTopic() + ",e:" + e.getMessage());
        }
    }

    /**
     * 同步发送消息，兼容老版本
     *
     * @param obj     raw message object
     * @param hashKey 用于hash后选择queue的key
     * @throws MQException 消息异常
     * @deprecated please use syncSendOrderly(Message message, String hashKey)
     */
    public void syncSendOrderly(Object obj, String hashKey) throws MQException {
        Message message = buildMessage(obj);
        if (StringUtils.isEmpty(hashKey)) {
            // fall back to normal
            syncSend(message);
        }
        try {
            SendResult sendResult = producer.send(message, messageQueueSelector, hashKey);
            log.debug("send rocketmq message orderly ,messageId : {}", sendResult.getMsgId());
            this.doAfterSyncSend(message, sendResult);
        } catch (Exception e) {
            log.error("顺序消息发送失败，topic : {}, msgObj : {}, e : {}", message.getTopic(), message, e);
            throw new MQException("顺序消息发送失败，topic :" + message.getTopic() + ",e:" + e.getMessage());
        }
    }

    /**
     * 重写此方法处理发送后的逻辑
     *
     * @param sendResult 发送结果
     */
    public void doAfterSyncSend(Message message, SendResult sendResult) {
    }

    /**
     * 异步发送消息
     *
     * @param message      msgObj
     * @param sendCallback 回调
     * @throws MQException 消息异常
     */
    public void asyncSend(Message message, SendCallback sendCallback) throws MQException {
        try {
            producer.send(message, sendCallback);
            log.debug("send rocketmq message async");
        } catch (Exception e) {
            log.error("消息发送失败，topic : {}, msgObj : {}, e : {}", message.getTopic(), message, e);
            throw new MQException("消息发送失败，topic :" + message.getTopic() + ",e:" + e.getMessage());
        }
    }

    /**
     * 异步发送消息,兼容老版本
     *
     * @param obj          raw message object
     * @param sendCallback 回调
     * @throws MQException 消息异常
     * @deprecated please use asyncSend(Message message, SendCallback sendCallback)
     */
    public void asyncSend(Object obj, SendCallback sendCallback) throws MQException {
        Message message = buildMessage(obj);
        try {
            producer.send(message, sendCallback);
            log.debug("send rocketmq message async");
        } catch (Exception e) {
            log.error("消息发送失败，topic : {}, msgObj : {}, e : {}", message.getTopic(), message, e);
            throw new MQException("消息发送失败，topic :" + message.getTopic() + ",e:" + e.getMessage());
        }
    }

    class DefaultSendCallback implements SendCallback {
        @Override
        public void onSuccess(SendResult sendResult) {
            log.debug("async send mq msg success: {}", sendResult);
        }

        @Override
        public void onException(Throwable e) {
            log.error("async send mq msg error: {}", e);
        }
    }

    /**
     * 异步发送消息，设置默认回调函数
     *
     * @param message msgObj
     * @throws MQException 消息异常
     */
    public void asyncSend(Message message) throws MQException {
        try {
            producer.send(message, new DefaultSendCallback());
            log.debug("send rocketmq message async");
        } catch (Exception e) {
            log.error("消息发送失败，topic : {}, msgObj : {}, e : {}", message.getTopic(), message, e);
            throw new MQException("消息发送失败，topic :" + message.getTopic() + ",e:" + e.getMessage());
        }
    }

}
