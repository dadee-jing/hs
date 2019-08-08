package com.hs.rs.persistence.dao;

import com.hs.rs.persistence.entity.MonitorDataLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorDataRepository extends JpaRepository<MonitorDataLog, String> {

    List<MonitorDataLog> findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(int uploadStatus);
}
