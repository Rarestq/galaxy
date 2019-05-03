package com.wuxiu.galaxy.service.core.base.utils;

import com.wuxiu.galaxy.service.core.base.enums.SmsTypeEnum;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.SmsBody;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 短信模板工具类
 *
 * @author: wuxiu
 * @date: 2019/5/3 08:47
 */
public class SmsTemplateUtils {

    private static final String TEMPLATE_PREFIX = "【存易天-Galaxy】";

    /**
     * 根据发送短信的类型指定具体的内容模板
     *
     * @param smsBody
     * @return
     */
    public static String getSmsContentBySmsType(SmsBody smsBody) {
        String smsContent = StringUtils.EMPTY;
        if (Objects.equals(SmsTypeEnum.FINISH_STORAGE_SMS_TYPE.getCode(),
                smsBody.getSmsType())) {
            smsContent = getStorageSmsContent(smsBody);
        } else if (Objects.equals(SmsTypeEnum.BEFORE_OVERDUE_SMS_TYPE.getCode(),
                smsBody.getSmsType())) {
            smsContent = getBeforeOverdueSmsContent(smsBody);
        } else if (Objects.equals(SmsTypeEnum.OVERDUE_SMS_TYPE.getCode(),
                smsBody.getSmsType())) {
            smsContent = getOverdueSmsContent(smsBody);
        }

        return smsContent;
    }

    /**
     * 将 smsBody 中的属性拼接成字符串(寄存完成的短信内容)
     *
     * @param smsBody
     * @return
     */
    public static String getStorageSmsContent(SmsBody smsBody) {
        StringBuilder builder = new StringBuilder(70);
        builder.append(TEMPLATE_PREFIX)
                .append("尊敬的 ").append(smsBody.getDepositorName())
                .append(" 先生/女士，").append("您的寄存编号为 ")
                .append(smsBody.getStorageRecordNo()).append(" ,")
                .append("请您在 ").append(smsBody.getStorageEndTime())
                .append(" 前进行取件，").append("以免给您带来不必要的麻烦。详情可联系 ")
                .append(smsBody.getAdminPhone()).append(", 谢谢您~");

        return builder.toString();
    }

    /**
     * 将 smsBody 中的属性拼接成字符串(逾期前的短信内容)
     *
     * @param smsBody
     * @return
     */
    public static String getBeforeOverdueSmsContent(SmsBody smsBody) {
        StringBuilder builder = new StringBuilder(70);
        builder.append(TEMPLATE_PREFIX)
                .append("尊敬的 ").append(smsBody.getDepositorName())
                .append(" 先生/女士，").append("您寄存编号为 ")
                .append(smsBody.getStorageRecordNo()).append(" 的行李即将逾期,")
                .append("请您在 ").append(smsBody.getStorageEndTime())
                .append(" 前及时取件，").append("以免给您带来不必要的麻烦。详情可联系 ")
                .append(smsBody.getAdminPhone()).append(", 谢谢您~");

        return builder.toString();
    }

    /**
     * 将 smsBody 中的属性拼接成字符串(逾期的短信内容)
     *
     * @param smsBody
     * @return
     */
    public static String getOverdueSmsContent(SmsBody smsBody) {
        StringBuilder builder = new StringBuilder(70);
        builder.append(TEMPLATE_PREFIX)
                .append("尊敬的 ").append(smsBody.getDepositorName())
                .append(" 先生/女士，").append("您寄存编号为 ")
                .append(smsBody.getStorageRecordNo()).append(" 的行李已逾期,")
                .append("请您及时进行取件，")
                .append("有任何疑问可以联系 ")
                .append(smsBody.getAdminPhone()).append(", 谢谢您~");

        return builder.toString();
    }
}
