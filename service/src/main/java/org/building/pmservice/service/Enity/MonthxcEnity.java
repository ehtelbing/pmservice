package org.building.pmservice.service.Enity;

public class MonthxcEnity {
    private String V_SYSTEM;
    private String V_YEAR;
    private String V_MONTH;
    private String V_ORG_CODE;
    private String V_DEPT_CODE;

    public String getV_SYSTEM() {
        return V_SYSTEM;
    }

    public void setV_SYSTEM(String v_SYSTEM) {
        V_SYSTEM = v_SYSTEM;
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

    /**
     * date format :YYYY/mm/dd  VARCHAR2
     */


    public String getV_ORG_CODE() {
        return V_ORG_CODE;
    }

    public void setV_ORG_CODE(String v_org_code) {
        V_ORG_CODE = v_org_code;
    }

    public String getV_DEPT_CODE() {
        return V_DEPT_CODE;
    }

    public void setV_DEPT_CODE(String v_dept_code) {
        V_DEPT_CODE = v_dept_code;
    }
}
