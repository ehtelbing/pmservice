package org.building.pmservice.service.Enity;

import java.util.List;

public class WorkOrderRetOEnity {
    private String V_ORDERGUID;
    private String V_ORDER_TYP_TXT;
    private String V_FUNC_LOC;
    private String V_EQUIP_NO;
    private String V_EQUIP_NAME;
    private String D_START_DATE;
    private String D_FINISH_DATE;
    private String V_SHORT_TXT;
    private String V_WBS;
    private String V_WBS_TXT;
    private String V_ENTERED_BY;
    private String D_ENTER_DATE;
    private String V_ORDER_STATUS;
    private String V_DEPTNAME;
    private String V_DEPTCODEREPARIRNAME;
    private String V_DEFECTGUID;
    private String V_STATECODE_STATUS;
    private String D_FACT_START_DATE;
    private String D_FACT_FINISH_DATE;
    private String V_SEND_STATE;
    private String V_MONEY;
    private String V_STR01;
    private String V_STR02;
    private String V_STR03;
    private String V_STR04;
    private String V_STR05;

    private List<WorkOrderRetTEnity> itemsMM;
    private List<WWDefReturnEnity> dgitems;

    public List<WWDefReturnEnity> getDgitems() {
        return dgitems;
    }

    public void setDgitems(List<WWDefReturnEnity> dgitems) {
        this.dgitems = dgitems;
    }

    public String getV_ORDERGUID() {
        return V_ORDERGUID;
    }

    public void setV_ORDERGUID(String v_ORDERGUID) {
        V_ORDERGUID = v_ORDERGUID;
    }

    public String getV_ORDER_TYP_TXT() {
        return V_ORDER_TYP_TXT;
    }

    public void setV_ORDER_TYP_TXT(String v_ORDER_TYP_TXT) {
        V_ORDER_TYP_TXT = v_ORDER_TYP_TXT;
    }

    public String getV_FUNC_LOC() {
        return V_FUNC_LOC;
    }

    public void setV_FUNC_LOC(String v_FUNC_LOC) {
        V_FUNC_LOC = v_FUNC_LOC;
    }

    public String getV_EQUIP_NO() {
        return V_EQUIP_NO;
    }

    public void setV_EQUIP_NO(String v_EQUIP_NO) {
        V_EQUIP_NO = v_EQUIP_NO;
    }

    public String getV_EQUIP_NAME() {
        return V_EQUIP_NAME;
    }

    public void setV_EQUIP_NAME(String v_EQUIP_NAME) {
        V_EQUIP_NAME = v_EQUIP_NAME;
    }

    public String getD_START_DATE() {
        return D_START_DATE;
    }

    public void setD_START_DATE(String d_START_DATE) {
        D_START_DATE = d_START_DATE;
    }

    public String getD_FINISH_DATE() {
        return D_FINISH_DATE;
    }

    public void setD_FINISH_DATE(String d_FINISH_DATE) {
        D_FINISH_DATE = d_FINISH_DATE;
    }

    public String getV_SHORT_TXT() {
        return V_SHORT_TXT;
    }

    public void setV_SHORT_TXT(String v_SHORT_TXT) {
        V_SHORT_TXT = v_SHORT_TXT;
    }

    public String getV_WBS() {
        return V_WBS;
    }

    public void setV_WBS(String v_WBS) {
        V_WBS = v_WBS;
    }

    public String getV_WBS_TXT() {
        return V_WBS_TXT;
    }

    public void setV_WBS_TXT(String v_WBS_TXT) {
        V_WBS_TXT = v_WBS_TXT;
    }

    public String getV_ENTERED_BY() {
        return V_ENTERED_BY;
    }

    public void setV_ENTERED_BY(String v_ENTERED_BY) {
        V_ENTERED_BY = v_ENTERED_BY;
    }

    public String getD_ENTER_DATE() {
        return D_ENTER_DATE;
    }

    public void setD_ENTER_DATE(String d_ENTER_DATE) {
        D_ENTER_DATE = d_ENTER_DATE;
    }

    public String getV_ORDER_STATUS() {
        return V_ORDER_STATUS;
    }

    public void setV_ORDER_STATUS(String v_ORDER_STATUS) {
        V_ORDER_STATUS = v_ORDER_STATUS;
    }

    public String getV_DEPTNAME() {
        return V_DEPTNAME;
    }

    public void setV_DEPTNAME(String v_DEPTNAME) {
        V_DEPTNAME = v_DEPTNAME;
    }

    public String getV_DEPTCODEREPARIRNAME() {
        return V_DEPTCODEREPARIRNAME;
    }

    public void setV_DEPTCODEREPARIRNAME(String v_DEPTCODEREPARIRNAME) {
        V_DEPTCODEREPARIRNAME = v_DEPTCODEREPARIRNAME;
    }

    public String getV_DEFECTGUID() {
        return V_DEFECTGUID;
    }

    public void setV_DEFECTGUID(String v_DEFECTGUID) {
        V_DEFECTGUID = v_DEFECTGUID;
    }

    public String getV_STATECODE_STATUS() {
        return V_STATECODE_STATUS;
    }

    public void setV_STATECODE_STATUS(String v_STATECODE_STATUS) {
        V_STATECODE_STATUS = v_STATECODE_STATUS;
    }

    public String getD_FACT_START_DATE() {
        return D_FACT_START_DATE;
    }

    public void setD_FACT_START_DATE(String d_FACT_START_DATE) {
        D_FACT_START_DATE = d_FACT_START_DATE;
    }

    public String getD_FACT_FINISH_DATE() {
        return D_FACT_FINISH_DATE;
    }

    public void setD_FACT_FINISH_DATE(String d_FACT_FINISH_DATE) {
        D_FACT_FINISH_DATE = d_FACT_FINISH_DATE;
    }

    public String getV_SEND_STATE() {
        return V_SEND_STATE;
    }

    public void setV_SEND_STATE(String v_SEND_STATE) {
        V_SEND_STATE = v_SEND_STATE;
    }

    public String getV_MONEY() {
        return V_MONEY;
    }

    public void setV_MONEY(String v_MONEY) {
        V_MONEY = v_MONEY;
    }

    public String getV_STR01() {
        return V_STR01;
    }

    public void setV_STR01(String v_STR01) {
        V_STR01 = v_STR01;
    }

    public String getV_STR02() {
        return V_STR02;
    }

    public void setV_STR02(String v_STR02) {
        V_STR02 = v_STR02;
    }

    public String getV_STR03() {
        return V_STR03;
    }

    public void setV_STR03(String v_STR03) {
        V_STR03 = v_STR03;
    }

    public String getV_STR04() {
        return V_STR04;
    }

    public void setV_STR04(String v_STR04) {
        V_STR04 = v_STR04;
    }

    public String getV_STR05() {
        return V_STR05;
    }

    public void setV_STR05(String v_STR05) {
        V_STR05 = v_STR05;
    }

    public List<WorkOrderRetTEnity> getItemsMM() {
        return itemsMM;
    }

    public void setItemsMM(List<WorkOrderRetTEnity> itemsMM) {
        this.itemsMM = itemsMM;
    }
}
