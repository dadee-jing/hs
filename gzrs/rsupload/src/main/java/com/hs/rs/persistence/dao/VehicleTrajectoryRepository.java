package com.hs.rs.persistence.dao;

import com.hs.rs.persistence.entity.VehicleTrajectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTrajectoryRepository extends JpaRepository<VehicleTrajectory, String> {

    List<VehicleTrajectory> findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(int uploadStatus);
}
