package com.hs.platform.station.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Entity基类
 *
 * @author ruoyi
 */
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    @PrePersist
    public void prePersist() {
        Date currentDate = new Date();
        createTime = Optional.ofNullable(createTime).orElse(currentDate);
        updateTime = Optional.ofNullable(updateTime).orElse(currentDate);
    }

    @PreUpdate
    public void preUpdate() {
        updateTime = new Date();
    }

}
