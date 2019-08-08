package com.hs.rs.persistence.dao;

import com.hs.rs.persistence.entity.Line;
import com.hs.rs.persistence.entity.LineMultiKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineRepository extends JpaRepository<Line, LineMultiKeys> {
    List<Line> findTop200ByUpLoadStatusIsNotOrderByUpLoadStatusDesc(int uploadStatus);
}
