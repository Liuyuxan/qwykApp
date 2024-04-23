package com.qywk.health.controller;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.health.pojo.ao.ProblemAO;
import com.qywk.health.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qlh
 * @date 2024/03/20 20:49
 * @description 问卷调查相关
 */
@RestController
@RequestMapping("questionnaire")
public class QuestionnaireController {
    @Autowired
    QuestionnaireService questionnaireService;

    /**
     * 提交文件结果
     * @param userId
     * @param form
     * @return
     */
    @PostMapping("submit")
    public ResultBody submit(@RequestHeader(value = "user_id", defaultValue = "test") String userId,
                             @RequestBody List<ProblemAO> form){
        return questionnaireService.submit(userId ,form);
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
        return questionnaireService.getAllProblem(page, size);
    }


    @GetMapping("/first/sent")
    public ResultBody firstSent(@RequestHeader(value = "user_id") String userId){
        return questionnaireService.firstSent(userId);
    }




}
