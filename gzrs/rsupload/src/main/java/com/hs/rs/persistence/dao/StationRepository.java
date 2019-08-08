package com.hs.rs.persistence.dao;


import com.hs.rs.persistence.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {
    List<Station> findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(int uploadStatus);
}
