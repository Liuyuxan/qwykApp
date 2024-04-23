package com.qywk.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qywk.common.core.constant.EnableConstants;
import com.qywk.common.core.entity.ResultBody;
import com.qywk.common.core.utils.PageUtils;
import com.qywk.common.redis.customenum.RedisKeyEnum;
import com.qywk.common.redis.service.RedisService;
import com.qywk.health.mapper.PlantMapper;
import com.qywk.health.mapper.PlantUserMapper;
import com.qywk.health.pojo.dto.PlantDTO;
import com.qywk.health.pojo.dto.PlantUserDTO;
import com.qywk.health.pojo.vo.PlantVO;
import com.qywk.health.service.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author qlh
 * @date 2024/04/22 22:39
 * @description
 */
@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantMapper plantMapper;

    @Autowired
    PlantUserMapper plantUserMapper;

    @Autowired
    RedisService redisService;


    @Override
    public ResultBody queryAll(String userId, Integer page, Integer size) {

        String plantKey = RedisKeyEnum.PLANT_INFO.create("info");
        List<PlantDTO> list = null;
        if(redisService.hasKey(plantKey)){
            list = redisService.getCacheObject(plantKey);
        }else {
            list = plantMapper.selectList(new QueryWrapper<PlantDTO>().eq("enable", EnableConstants.ACTIVATION));
        }
        PageUtils<PlantDTO> pageUtils = new PageUtils<>(list, page, size);

        List<PlantUserDTO> plantUserDTOList = plantUserMapper.selectList(
                new QueryWrapper<PlantUserDTO>()
                        .eq("user_id", userId)
        );

        Set<String> set = new HashSet<>();
        for(PlantUserDTO dto : plantUserDTOList){
            if(dto.getEnable().equals(EnableConstants.ACTIVATION)){
                set.add(dto.getPlantId());
            }
        }

        List<PlantVO> records = new ArrayList<>();
        for(PlantDTO dto : pageUtils.getRecords()){
            PlantVO plantVO = new PlantVO();
            BeanUtils.copyProperties(dto, plantVO);
            plantVO.setUnlockedStatus(set.contains(dto.getId()));
        }
        return ResultBody.ok().data("total", pageUtils.getTotal()).data("records", records);
    }
}
