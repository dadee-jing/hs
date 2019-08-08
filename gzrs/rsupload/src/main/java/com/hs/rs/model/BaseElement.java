package com.hs.rs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BaseElement {

    @XStreamAlias("datatype")
    private String datatype;

    @XStreamAlias("datas")
    private String datas;

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }
}
