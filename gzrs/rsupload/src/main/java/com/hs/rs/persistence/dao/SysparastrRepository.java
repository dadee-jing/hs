package com.hs.rs.persistence.dao;

import com.hs.rs.persistence.entity.Sysparastr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysparastrRepository extends JpaRepository<Sysparastr, String> {
        Sysparastr findFirstByTypeNameEn(String typeNameEn);

}
