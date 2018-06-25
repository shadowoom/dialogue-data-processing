package com.i2r.object;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * multimedia-data-processing
 * com.i2r.object
 * Created by Zhang Chen
 * 6/22/2018
 */

public class Dialogue {

    private String id;

    private String lang;

    @JacksonXmlProperty(localName = "system_name")
    private String systemName;

    @JacksonXmlProperty(localName = "user_name")
    private String userName;

    @JacksonXmlProperty(localName = "timestamp")
    private String timeStamp;

    private List<Turn> turns;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }
}
