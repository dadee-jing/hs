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
public class VehicleInfoMultiKeys implements Serializable {

    private String hphm;

    private String cpys;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleInfoMultiKeys)) return false;
        VehicleInfoMultiKeys that = (VehicleInfoMultiKeys) o;
        return getHphm().equals(that.getHphm()) &&
                getCpys().equals(that.getCpys());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHphm(), getCpys());
    }
}
