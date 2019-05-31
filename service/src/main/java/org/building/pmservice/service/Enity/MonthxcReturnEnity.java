package org.building.pmservice.service.Enity;

import java.util.ArrayList;
import java.util.List;

public class MonthxcReturnEnity {
    private String SYSNAME;
    private String V_YEAR;
    private String V_MONTH;
    private String V_ORGCODE;
    private String V_DEPTCODE;

    private String V_INFO;
    private String V_TYPE;

    private List<MonthxcLReturnEnity> Items;

    public String getSYSNAME() {
        return SYSNAME;
    }

    public void setSYSNAME(String SYSNAME) {
        this.SYSNAME = SYSNAME;
    }

    public String getV_YEAR() {
        return V_YEAR;
    }

    public void setV_YEAR(String v_YEAR) {
        V_YEAR = v_YEAR;
    }

    public String getV_MONTH() {
        return V_MONTH;
    }

    public void setV_MONTH(String v_MONTH) {
        V_MONTH = v_MONTH;
    }

    public String getV_ORGCODE() {
        return V_ORGCODE;
    }

    public void setV_ORGCODE(String v_ORGCODE) {
        V_ORGCODE = v_ORGCODE;
    }

    public String getV_DEPTCODE() {
        return V_DEPTCODE;
    }

    public void setV_DEPTCODE(String v_DEPTCODE) {
        V_DEPTCODE = v_DEPTCODE;
    }

    public List<MonthxcLReturnEnity> getItems() {
        return Items;
    }

    public void setItems(List<MonthxcLReturnEnity> items) {
        Items = items;
    }

    public String getV_INFO() {
        return V_INFO;
    }

    public void setV_INFO(String v_INFO) {
        V_INFO = v_INFO;
    }

    public String getV_TYPE() {
        return V_TYPE;
    }

    public void setV_TYPE(String v_TYPE) {
        V_TYPE = v_TYPE;
    }
}
