package com.hs.platform.station.persistence.remote.dao;

import com.hs.platform.station.persistence.remote.entity.RemoteWeightDataData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemoteWeightDataRepository extends JpaRepository<RemoteWeightDataData, Long> {
}
