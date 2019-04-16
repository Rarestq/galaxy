package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.service.LuggageStorageRecordFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 17:03
 */
@Service
public class LuggageStorageRecordClient {

    @Autowired
    private LuggageStorageRecordFacade storageRecordFacade;
}
