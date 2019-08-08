package com.hs.rs.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by zzw on 17-2-15.
 */
@XStreamAlias("soapenv:Envelope")
public class Envelope {

    @XStreamAsAttribute
    @XStreamAlias("xmlns:soapenv")
    private String xmlnsSoapenv = "http://schemas.xmlsoap.org/soap/envelope/";

    @XStreamAsAttribute
    @XStreamAlias("xmlns:web")
    private String xmlnsWeb = "http://webservice.gzjdc.grkj.com/";

    @XStreamAlias("soapenv:Body")
    private Body body;

    public String getXmlnsSoapenv() {
        return xmlnsSoapenv;
    }

    public void setXmlnsSoapenv(String xmlnsSoapenv) {
        this.xmlnsSoapenv = xmlnsSoapenv;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getXmlnsWeb() {
        return xmlnsWeb;
    }

    public void setXmlnsWeb(String xmlnsWeb) {
        this.xmlnsWeb = xmlnsWeb;
    }

    public static Envelope BuildEnvelope(String datatype, String datas) {
        Envelope envelope = new Envelope();
        Body body1 = new Body();
        BaseElement baseElement = new BaseElement();
        baseElement.setDatatype(datatype);
        baseElement.setDatas(datas);
        switch (datatype) {
            case "monitoringpoint":
                body1.setMonitoringpoint(baseElement);
                break;
            case "monitoringline":
                body1.setMonitoringline(baseElement);
                break;
            case "mobilemonitor":
                body1.setMobilemonitor(baseElement);
                break;
            case "vehicletrajectory":
                body1.setVehicletrajectory(baseElement);
                break;
            case "trafficflow":
                body1.setTrafficflow(baseElement);
                break;
            case "monitoringdata":
                body1.setMonitoringdata(baseElement);
                break;
            case "environmentalquality":
                body1.setEnvironmentalquality(baseElement);
                break;
            case "vehicle":
                body1.setVehicle(baseElement);
                break;
            case "blacksmokevehicle":
                body1.setBlacksmokevehicle(baseElement);
                break;
            default:
                ;
        }
        envelope.setBody(body1);
        return envelope;
    }
}
