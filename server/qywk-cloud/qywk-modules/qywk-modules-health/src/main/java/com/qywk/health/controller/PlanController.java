package com.qywk.health.controller;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.health.pojo.ao.PlanAO;
import com.qywk.health.pojo.ao.PlanSysAO;
import com.qywk.health.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qlh
 * @date 2024/04/23 12:19
 * @description 计划相关接口
 */
@RestController
@RequestMapping("plan")
public class PlanController {

    @Autowired
    PlanService planService;

    /**
     * 查询系统推荐的计划的分区
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("querySysSubarea")
    public ResultBody querySysSubarea(@RequestHeader(value = "user_id") String userId,
                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "size", defaultValue = "10") Integer size){
        return planService.querySysSubarea(userId, page, size);
    }


    /**
     * 查询系统推荐的计划
     * @param userId
     * @param page
     * @param size
     * @param subarea
     * @return
     */
    @GetMapping("querySysRecommend")
    public ResultBody querySysRecommend(@RequestHeader(value = "user_id") String userId,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                                        @RequestParam(value = "subarea") String subarea){
        return planService.querySysRecommend(userId, page, size, subarea);
    }

    /**
     * 选择计划
     * @param userId
     * @param aoList
     * @return
     */
    @PostMapping("choose")
    public ResultBody choose(@RequestHeader(value = "user_id") String userId,
                             @RequestBody List<PlanAO> aoList){
        return planService.choose(userId, aoList);
    }

    /**
     * 自定义创建计划
     * @param userId
     * @param ao
     * @return
     */
    @PostMapping("customize")
    public ResultBody customize(@RequestHeader(value = "user_id") String userId,
                                @RequestBody PlanSysAO ao){
        return planService.customize(userId, ao);
    }

    /**
     * 计划打卡
     * @param userId
     * @param planId
     * @return
     */
    @PostMapping("punch")
    public ResultBody punch(@RequestHeader(value = "user_id") String userId,
                            @RequestParam(value = "plan_id") String planId){
        return planService.punch(userId, planId);
    }

    @PostMapping("queryPunchState")
    public ResultBody queryPunchState(@RequestHeader(value = "user_id") String userId,
                                      @RequestParam(value = "subarea", defaultValue = "all") String subarea,
                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "size", defaultValue = "10") Integer size){
        return planService.queryPunchState(userId, subarea, page, size);
    }

}
