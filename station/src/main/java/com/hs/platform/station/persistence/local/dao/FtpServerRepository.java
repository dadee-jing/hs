package com.hs.platform.station.persistence.local.dao;

import com.hs.platform.station.persistence.local.entity.FtpServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FtpServerRepository extends JpaRepository<FtpServer, Long> {
}
