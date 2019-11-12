package com.ruoyi.duge.service;

import com.ruoyi.duge.domain.RefittedVehicle;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 改装车信息  服务层
 */
public interface RefittedVehicleService {

    /**
     * 根据主键查询改装车信息
     * @param id 主键
     * @return 改装车信息
     */
    RefittedVehicle getRefittedVehicleById(Integer id);


    /**
     * 查询改装车集合信息
     * @param refittedVehicle 改装车对象
     * @return 改装车集合信息
     */
    List<RefittedVehicle> listRefittedVehicles(RefittedVehicle refittedVehicle);
}
