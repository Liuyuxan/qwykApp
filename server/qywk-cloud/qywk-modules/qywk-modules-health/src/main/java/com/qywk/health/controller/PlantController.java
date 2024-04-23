package com.qywk.health.controller;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.health.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qlh
 * @date 2024/04/22 22:07
 * @description 植物相关接口
 */
@RestController
@RequestMapping("plant")
public class PlantController {

    @Autowired
    PlantService plantService;


    @GetMapping("queryAll")
    public ResultBody queryAll(@RequestHeader(value = "user_id") String userId,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        return plantService.queryAll(userId, page, size);
    }

}
