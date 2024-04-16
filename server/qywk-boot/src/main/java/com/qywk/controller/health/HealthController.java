package com.qywk.controller.health;

import com.qywk.common.entity.ResultBody;
import com.qywk.pojo.ao.ProblemAO;
import com.qywk.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qlh
 * @date 2024/03/20 20:49
 * @description
 */
@RestController
@RequestMapping("/health")
public class HealthController {
    @Autowired
    HealthService healthService;

    /**
     * 提交文件结果
     * @param userId
     * @param form
     * @return
     */
    @PostMapping("submit")
    public ResultBody submit(@RequestHeader(value = "user_id", defaultValue = "test") String userId,
                             @RequestBody List<ProblemAO> form){
        return healthService.submit(form);
    }

    /**
     * 获取问题集，问卷调查问题
     * @param page  页码
     * @param size  页数
     * @return
     */
    @GetMapping("getAllProblem")
    public ResultBody getAllProblem(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "5") Integer size){
        return healthService.getAllProblem(page, size);
    }




}
