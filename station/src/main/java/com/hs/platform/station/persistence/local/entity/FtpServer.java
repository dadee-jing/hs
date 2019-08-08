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
@Table(name = "ftp_server")
public class FtpServer {

    @Id
    private Long id;
    private String name;
    private String url;
}
