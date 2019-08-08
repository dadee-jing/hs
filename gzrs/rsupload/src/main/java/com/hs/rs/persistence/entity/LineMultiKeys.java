package com.hs.rs.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LineMultiKeys implements Serializable {

    /** 点位编号 */
    private String dwbh;
    /** 遥感线编号 */
    private String ycxbh;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineMultiKeys)) return false;
        LineMultiKeys that = (LineMultiKeys) o;
        return getDwbh().equals(that.getDwbh()) &&
                getYcxbh().equals(that.getYcxbh());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDwbh(), getYcxbh());
    }
}
