package com.qywk.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qywk.common.constant.EnableConstants;
import com.qywk.common.entity.ResultBody;
import com.qywk.mapper.ProblemMapper;
import com.qywk.pojo.ao.ProblemAO;
import com.qywk.pojo.dto.ProblemDTO;
import com.qywk.pojo.entity.HealthIndicator;
import com.qywk.pojo.entity.Indicator;
import com.qywk.pojo.vo.ProblemVO;
import com.qywk.service.HealthService;

import com.qywk.utils.ChatAiUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qlh
 * @date 2024/03/20 20:50
 * @description
 */
@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    ProblemMapper problemMapper;
    @Autowired
    ChatAiUtils chatAiUtils;

    @Override
    public ResultBody submit(List<ProblemAO> form) {
        StringBuilder problems = new StringBuilder();
        for(ProblemAO ao : form){
            problems.append("问题:").append(ao.getProblem()).append("回答:").append(ao.getOption()).append(";");
        }
        problems.append("通过上述问题，以100分制的表示，返回{气虚、阳虚、阴虚、痰湿、湿热、血瘀、气抑、特禀}的比例值并附加综合平均分数和一些调理建议（以json格式返回）以下格式 ```{[{\"indicators\": \"气虚\",\"score\": 55,},{\"indicators\": \"阳虚\",\"score\": 45,},...],\"avg\": 61,\"suggest\": \"您的体质可能偏向于...\"}```");
        StringBuilder report = new StringBuilder( chatAiUtils.inquiry(problems.toString()));
        report.delete(0, 7);
        report.delete(report.length() - 3, report.length());
        HealthIndicator indicator = JSONObject.parseObject(report.toString(), HealthIndicator.class);
        return ResultBody.ok().data(indicator);
    }

    /**
     * 获取问题集，问卷调查问题
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResultBody getAllProblem(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        QueryWrapper<ProblemDTO> wrapper = new QueryWrapper<>();
        wrapper.eq("enable", EnableConstants.ACTIVATION);
        Page<ProblemDTO> list = (Page<ProblemDTO>)problemMapper.selectList(wrapper);
        List<ProblemDTO> result = list.getResult();
        List<ProblemVO> records = new ArrayList<>();
        for (ProblemDTO dto : result){
            ProblemVO vo = new ProblemVO();
            BeanUtils.copyProperties(dto, vo);
            String[] split = dto.getOptionBox().split(";");
            vo.setOptionBox(Arrays.asList(split));
            records.add(vo);
        }
        return ResultBody.ok().data("total", list.getTotal()).data("records", records);
    }
}
