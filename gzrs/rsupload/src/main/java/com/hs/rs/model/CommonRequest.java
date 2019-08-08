package com.hs.rs.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonRequest {

    private String datatype;

    private JsonNode datas;

    private String istest;
}
