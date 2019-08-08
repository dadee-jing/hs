package com.hs.rs.persistence.dao;

import com.hs.rs.persistence.entity.TrafficFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficFlowRepository extends JpaRepository<TrafficFlow, Long> {

    List<TrafficFlow> findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(int uploadStatus);
}
