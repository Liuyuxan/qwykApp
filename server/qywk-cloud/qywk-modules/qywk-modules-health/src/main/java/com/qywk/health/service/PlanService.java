package com.qywk.health.service;

import com.qywk.common.core.entity.ResultBody;
import com.qywk.health.pojo.ao.PlanAO;
import com.qywk.health.pojo.ao.PlanSysAO;

import java.util.List;

/**
 * @author qlh
 * @date 2024/04/23 15:09
 * @description
 */
public interface PlanService {

    ResultBody querySysSubarea(String userId, Integer page, Integer size);

    ResultBody querySysRecommend(String userId, Integer page, Integer size, String subarea);

    ResultBody choose(String userId, List<PlanAO> aoList);

    ResultBody customize(String userId, PlanSysAO ao);

    ResultBody punch(String userId, String planId);

    ResultBody queryPunchState(String userId, String subarea, Integer page, Integer size);
}
