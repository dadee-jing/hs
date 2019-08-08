package com.hs.rs.persistence.dao;

import com.hs.rs.persistence.entity.BlacksmokevehicleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlacksmokevehicleInfoRepository extends JpaRepository<BlacksmokevehicleInfo, Long> {

    List<BlacksmokevehicleInfo> findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(int uploadStatus);
}
