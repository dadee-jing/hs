package com.hs.platform.station.persistence.local.dao;

import com.hs.platform.station.persistence.local.entity.ConfigData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigDataRepository extends JpaRepository<ConfigData, Long> {

    public ConfigData findFirstByKey(String key);
}
