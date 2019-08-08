package com.hs.platform.station.persistence.local.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "config_data")
public class ConfigData {

    @Id
    private Long id;
    private String key;
    private String value;
}
