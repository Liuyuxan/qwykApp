package com.qywk.health.service;

import com.qywk.common.core.entity.ResultBody;

/**
 * @author qlh
 * @date 2024/04/22 22:38
 * @description
 */
public interface PlantService {
    ResultBody queryAll(String userId, Integer page, Integer size);
}
