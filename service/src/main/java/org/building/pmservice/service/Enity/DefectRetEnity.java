package org.building.pmservice.service.Enity;

import java.util.List;

public class DefectRetEnity {
    private String SYSNAME;
    private String V_YEAR;
    private String V_MONTH;
    private String V_ORGCODE;
    private String V_DEPTCODE;

    private List<DefectRetOEnity> items;

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

    public List<DefectRetOEnity> getItems() {
        return items;
    }

    public void setItems(List<DefectRetOEnity> items) {
        this.items = items;
    }
}
