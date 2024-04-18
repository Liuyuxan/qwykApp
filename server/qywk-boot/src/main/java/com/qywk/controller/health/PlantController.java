package com.qywk.controller.health;

import com.qywk.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.qywk.common.entity.ResultBody;
/**
 * @author qlh
 * @date 2024/04/16 14:40
 * @description 植物形象状态接口
 */

@RestController
@RequestMapping("plant")
public class PlantController {

    @Autowired
    PlantService plantService;



    @GetMapping("get")
    public ResultBody getPlantImage(@RequestHeader("user_id") String userId,
                                    @RequestParam("id") String id){
        return plantService.getPlantImage(userId, id);
    }


    /**********************************  以下是提供后台管理员的接口，后续更改 *************************************/

    @GetMapping("/getAllPlantInfo")
    public ResultBody getAllPlantInfo(@RequestHeader("user_id") String userId){
        return null;
    }

}
