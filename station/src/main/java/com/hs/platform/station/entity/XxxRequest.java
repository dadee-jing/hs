package com.hs.platform.station.entity;

public class XxxRequest {

    private int action;
    private byte[] sequenceArray;
    private String jsonContent;

    public XxxRequest(int action, byte[] sequenceArray, String jsonContent) {
        this.action = action;
        this.sequenceArray = sequenceArray;
        this.jsonContent = jsonContent;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public byte[] getSequenceArray() {
        return sequenceArray;
    }

    public void setSequenceArray(byte[] sequenceArray) {
        this.sequenceArray = sequenceArray;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

}
