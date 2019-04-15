package com.wuxiu.galaxy.service.core.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * @author wuxiu
 */
public class UUIDGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UUIDGenerator.class);

    public static String getUUID() {
        UUID firstUUID = UUID.randomUUID();
        UUID secondUUID = UUID.randomUUID();
        Random random = new Random();
        int index = random.nextInt(10);
        String str = firstUUID.toString().substring(0, index) +
                secondUUID.toString().substring(index);
        str = str.replace("-", "").toUpperCase();

        return str;
    }

    public static void main(String[] args) {
        String uuid = getUUID();
        System.out.printf("随机产生的 id 为：" + uuid);
    }
}
