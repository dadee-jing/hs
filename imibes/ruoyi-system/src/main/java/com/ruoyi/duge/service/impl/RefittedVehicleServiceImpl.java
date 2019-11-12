package com.ruoyi.duge.service.impl;

import com.ruoyi.duge.domain.RefittedVehicle;
import com.ruoyi.duge.mapper.RefittedVehicleMapper;
import com.ruoyi.duge.service.RefittedVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 改装车信息  服务实现
 */
@Service
public class RefittedVehicleServiceImpl implements RefittedVehicleService {
    @Autowired
    private RefittedVehicleMapper refittedVehicleMapper;

    @Override
    public RefittedVehicle getRefittedVehicleById(Integer id) {
        return refittedVehicleMapper.getRefittedVehicleById(id);
    }

    @Override
    public List<RefittedVehicle> listRefittedVehicles(RefittedVehicle refittedVehicle) {
        return refittedVehicleMapper.listRefittedVehicles(refittedVehicle);
    }
}
