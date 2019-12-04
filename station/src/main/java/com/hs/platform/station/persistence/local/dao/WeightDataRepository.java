package com.hs.platform.station.persistence.local.dao;

import com.hs.platform.station.persistence.local.entity.WeightData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeightDataRepository extends JpaRepository<WeightData, Long> {

    public List<WeightData> findTop5ByUploadTagIsNotAndWeightingDateBeforeOrderByUploadTagAscIdAsc(Integer uploadTag, Date date);

    public List<WeightData> findTop10ByUploadTagIsNotAndWeightingDateBeforeOrderByUploadTagAscIdAsc(Integer uploadTag, Date date);

    public WeightData findWeightDataById(Long id);

    List<WeightData> findTop5ByUploadTagIsNotOrderByUploadTagDescIdAsc(Integer uploadTag);

    //public List<WeightData> findTop5ByUploadSjIsNotAndWeightingDateBeforeOrderByUploadSjAscIdAsc(Integer uploadSj, Date date);

}
