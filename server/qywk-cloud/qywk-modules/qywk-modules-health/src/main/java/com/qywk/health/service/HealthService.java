package com.qywk.health.service;


import com.qywk.common.core.entity.ResultBody;
import com.qywk.health.pojo.ao.ProblemAO;

import java.util.List;

/**
 * @author qlh
 * @date 2024/03/20 20:50
 * @description
 */
public interface HealthService {
    ResultBody submit(List<ProblemAO> form);

    /**
     * 获取问题集，问卷调查问题
     * @param page
     * @param size
     * @return
     */
    ResultBody getAllProblem(Integer page, Integer size);
}
