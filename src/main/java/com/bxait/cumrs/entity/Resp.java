//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bxait.cumrs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public class Resp {
    protected String data;
    protected Short code;
    protected String codeDesc;


    public Resp(Object data) {
        this(data, (short)200);
    }

    public Resp(Object data, Short code) {
        this(data, code, "");
    }

    public Resp(Object data, Short code, String codeDesc) {
        this.setData(data);
        this.code = code;
        this.codeDesc = codeDesc;
    }

    @JsonIgnore
    public <T> T getData(Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return (new ObjectMapper()).readValue(this.data, clazz);
    }

    public void setData(Object data) {
        try {
            if (data != null) {
                this.data = (new ObjectMapper()).writeValueAsString(data);
            }
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
        }

    }

    public String getdata() {
        return this.data;
    }

    public void setdata(String data) {
        this.data = data;
    }

    public Short getCode() {
        return this.code;
    }

    public Resp setCode(Short code) {
        this.code = code;
        return this;
    }

    public String getCodeDesc() {
        return this.codeDesc;
    }

    public Resp setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
        return this;
    }

    public String toJSON() throws JsonProcessingException {
        return (new ObjectMapper()).writeValueAsString(this);
    }
}
