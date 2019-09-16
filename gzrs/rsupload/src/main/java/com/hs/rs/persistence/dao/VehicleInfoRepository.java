package com.hs.rs.persistence.dao;

import com.hs.rs.persistence.entity.VehicleInfo;
import com.hs.rs.persistence.entity.VehicleInfoMultiKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleInfoRepository extends JpaRepository<VehicleInfo, VehicleInfoMultiKeys> {

    List<VehicleInfo> findTop200ByUpLoadStatusIsNullOrderByUpLoadStatusDesc();
}
