package com.hs.rs.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统参数配置表 sys_para_str
 *
 * @author ruoyi
 * @date 2019-07-25
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_para_str")
@Entity

public class Sysparastr {
    /**
     * 属性名称
     */

    private String paramName;
    /**
     * 属性值
     */
    private String paramValue;
    /**
     * 类型
     */
    @Id
    private String typeNameEn;




    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getTypeNameEn() {
        return typeNameEn;
    }

    public void setTypeNameEn(String typeNameEn) {
        this.typeNameEn = typeNameEn;
    }
}
