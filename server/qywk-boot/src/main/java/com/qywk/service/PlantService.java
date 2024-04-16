package com.qywk.service;

import com.qywk.common.entity.ResultBody;

/**
 * @author qlh
 * @date 2024/04/16 14:46
 * @description
 */
public interface PlantService {
    ResultBody getPlantImage(String username, String id);
}
