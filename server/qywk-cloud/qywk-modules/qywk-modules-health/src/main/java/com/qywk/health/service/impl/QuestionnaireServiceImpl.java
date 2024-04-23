package com.qywk.health.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.qywk.common.core.constant.EnableConstants;
import com.qywk.common.core.entity.ResultBody;
import com.qywk.common.core.utils.PageUtils;
import com.qywk.health.client.ChatAiClient;
import com.qywk.health.mapper.ProblemMapper;
import com.qywk.health.mapper.QuestionnaireResultMapper;
import com.qywk.health.pojo.ao.ProblemAO;
import com.qywk.health.pojo.dto.ProblemDTO;
import com.qywk.health.pojo.dto.QuestionnaireResultDTO;
import com.qywk.health.pojo.entity.HealthIndicator;
import com.qywk.health.pojo.entity.Indicator;
import com.qywk.health.pojo.vo.ProblemVO;
import com.qywk.health.service.QuestionnaireService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    ProblemMapper problemMapper;
    @Autowired
    QuestionnaireResultMapper questionnaireResultMapper;
    @Autowired
    ChatAiClient chatAiClient;

    @Override
    public ResultBody submit(String userId, List<ProblemAO> form) {
        try {
            // 根据实际需求，定义体质指标数组
            Indicator[] indicators = new Indicator[]{
                    new Indicator("气虚", 0),
                    new Indicator("阳虚", 0),
                    new Indicator("阴虚", 0),
                    new Indicator("痰湿", 0),
                    new Indicator("湿热", 0),
                    new Indicator("血瘀", 0),
                    new Indicator("气抑", 0),
                    new Indicator("特禀", 0)
            };

            StringBuilder prefix = new StringBuilder("请根据下面的问卷回答，给出[气虚, 阳虚, 阴虚, 痰湿, 湿热, 血瘀, 气抑, 特禀]" +
                    "这几个方面给出对应的分数，每个标签都是100分制(严格的只给我8个数字，不要多余的中文解释，每个数以分号;隔开) : ");
            StringBuilder problem = new StringBuilder();
            // 计算每个体质指标的分数
            for (ProblemAO answer : form) {
                problem.append("Q:").append(answer.getProblem()).append("A:").append(answer.getOption());
            }

            String res = chatAiClient.inquiry(prefix.append(problem).toString());

            String[] score = res.split(";");
            List<String> constitution = new ArrayList<>();

            int sum = 800;
            int max = 0;
            int idx = 0;
            for(int i = 0; i < score.length; i++){
                int t = Integer. parseInt(score[i]);
                indicators[i].setScore(t);
                if(t >= 50) constitution.add(indicators[i].getInfo() + "质");
                sum -= indicators[i].getScore();
                if(max < t){
                    max = t;
                    idx = i;
                }
            }

            if(constitution.isEmpty()){
                constitution.add(indicators[idx].getInfo() + "质");
            }else {
                constitution.remove(indicators[idx].getInfo() + "质");
                constitution.add(0, indicators[idx].getInfo() + "质");
            }
            String compareInfo = chatAiClient.inquiry(problem + ";更具给出的回答给出大概的结论， 严格返回给我的信息只要这几个字，例子如下：{你的体质在xx-xx岁的x性中，优于xx%的人}，只修改xx的内容");
            String suggest = chatAiClient.inquiry(  "请对 " + indicators[idx].getInfo() + "体质给出20~30个字的简单的解释与科普");
            // 初始化健康指标对象
            HealthIndicator healthIndicator = new HealthIndicator(indicators, constitution, sum / 8, compareInfo, suggest);
            QuestionnaireResultDTO result = new QuestionnaireResultDTO();
            result.setUserId(userId);
            result.setJson(JSONObject.toJSONString(healthIndicator));
            questionnaireResultMapper.insert(result);
            return ResultBody.ok().data("report", healthIndicator);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return ResultBody.error().message("系统内部错误");
        }
    }

    /**
     * 获取问题集，问卷调查问题
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResultBody getAllProblem(Integer page, Integer size) {
        QueryWrapper<ProblemDTO> wrapper = new QueryWrapper<>();
        wrapper.eq("enable", EnableConstants.ACTIVATION);
        List<ProblemDTO> list = problemMapper.selectList(wrapper);

        PageUtils<ProblemDTO> pageUtils = new PageUtils<>(list, page, size);
        List<ProblemDTO> result = pageUtils.getRecords();
        List<ProblemVO> records = new ArrayList<>();
        for (ProblemDTO dto : result){
            ProblemVO vo = new ProblemVO();
            BeanUtils.copyProperties(dto, vo);
            String[] split = dto.getOptionBox().split(";");
            vo.setOptionBox(Arrays.asList(split));
            records.add(vo);
        }
        return ResultBody.ok().data("total", pageUtils.getTotal()).data("records", records);
    }

    @Override
    public ResultBody firstSent(String userId) {
        if(!questionnaireResultMapper.selectList(new QueryWrapper<QuestionnaireResultDTO>().eq("enable", EnableConstants.ACTIVATION).eq("user_id", userId)).isEmpty()){
            return ResultBody.error().message("已首次填写");
        }else {
            return ResultBody.ok().message("请填写健康问卷");
        }
    }
}
