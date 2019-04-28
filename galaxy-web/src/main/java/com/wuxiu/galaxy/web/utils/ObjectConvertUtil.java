package com.wuxiu.galaxy.web.utils;

import com.google.common.collect.Lists;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.web.biz.vo.Pair;

import java.util.List;

/**
 *
 * 对象转换工具类
 *
 * @author: wuxiu
 * @date: 2019/3/11 16:00
 */
public class ObjectConvertUtil {

    /**
     * 将 PairDTO 转换为 Pair 对象
     *
     * @param pairDTOs
     * @return
     */
    public static List<Pair<Long, String>> convertDTO2Domain(List<PairDTO<Long, String>> pairDTOs) {
        List<Pair<Long, String>> pairs = Lists.newArrayList();
        pairDTOs.forEach(pairDTO -> {
            Pair<Long, String> pair = new Pair<>();
            pair.setKey(pairDTO.getKey());
            pair.setValue(pairDTO.getValue());
            pairs.add(pair);
        });

        return pairs;
    }

    /**
     * 将 PairDTO 转换为 Pair 对象
     *
     * @param pairDTOs
     * @return
     */
    public static List<Pair<String, String>> convertStrDTO2Domain(List<PairDTO<String, String>> pairDTOs) {
        List<Pair<String, String>> pairs = Lists.newArrayList();
        pairDTOs.forEach(pairDTO -> {
            Pair<String, String> pair = new Pair<>();
            pair.setKey(pairDTO.getKey());
            pair.setValue(pairDTO.getValue());
            pairs.add(pair);
        });

        return pairs;
    }
}
