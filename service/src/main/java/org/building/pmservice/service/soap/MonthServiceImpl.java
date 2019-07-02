package org.building.pmservice.service.soap;


import org.building.pmservice.service.Enity.*;
import org.building.pmservice.service.repository.MonthRepository;
import org.building.pmservice.service.repository.WxjhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService
@Component
public class MonthServiceImpl implements MonthService {
    @Autowired
    private MonthRepository monthRepository;
    @Autowired
    private WxjhRepository wxjhRepository;

    @Override
    public MonthxcReturnEnity Monxc(MonthxcEnity items) {
        MonthxcReturnEnity ret = new MonthxcReturnEnity();
        try {
            ret.setV_YEAR(items.getV_YEAR());
            ret.setV_MONTH(items.getV_MONTH());
            ret.setV_DEPTCODE(items.getV_DEPT_CODE());
            ret.setV_ORGCODE(items.getV_ORG_CODE());
            ret.setSYSNAME("SBGL");

            Map result = monthRepository.OutMonthData(items.getV_YEAR(), items.getV_MONTH(), items.getV_ORG_CODE(), items.getV_DEPT_CODE());
            if (result.size() > 0) {
                if (result.get("RET").equals("SUCCESS")) {
                    List<MonthxcLReturnEnity> lists = new ArrayList<>();
                    List mlist = (List) result.get("list");
                    for (int m = 0; m < mlist.size(); m++) {
                        Map cmap = (Map) mlist.get(m);
                        MonthxcLReturnEnity monthxcLReturnEnity = new MonthxcLReturnEnity();

                        monthxcLReturnEnity.setV_GUID(cmap.get("V_GUID").toString());
                        monthxcLReturnEnity.setV_EQUTYPECODE(cmap.get("V_EQUTYPECODE").toString());
                        monthxcLReturnEnity.setV_EQUCODE(cmap.get("V_EQUCODE").toString());
                        monthxcLReturnEnity.setV_REPAIRMAJOR(cmap.get("V_REPAIRMAJOR_CODE").toString());
                        monthxcLReturnEnity.setV_CONTENT(cmap.get("V_CONTENT").toString());
                        monthxcLReturnEnity.setV_STARTTIME(cmap.get("V_STARTTIME").toString());

                        monthxcLReturnEnity.setV_ENDTIME(cmap.get("V_ENDTIME").toString());
                        monthxcLReturnEnity.setV_HOUR(cmap.get("V_HOUR").toString());
                        monthxcLReturnEnity.setV_INDATE(cmap.get("V_INDATE").toString());
                        monthxcLReturnEnity.setV_INPER(cmap.get("V_INPER").toString());
                        monthxcLReturnEnity.setV_BZ(cmap.get("V_BZ").toString());
                        monthxcLReturnEnity.setV_STATE(cmap.get("V_STATE").toString());

                        monthxcLReturnEnity.setV_MAIN_DEFECT(cmap.get("V_MAIN_DEFECT").toString());
                        monthxcLReturnEnity.setV_EXPECT_AGE(cmap.get("V_EXPECT_AGE").toString());
                        monthxcLReturnEnity.setV_REPAIR_PER(cmap.get("V_REPAIR_PER").toString());
                        monthxcLReturnEnity.setV_SGWAYNAME(cmap.get("V_SGWAYNAME").toString());
                        monthxcLReturnEnity.setV_FLAG(cmap.get("V_FLAG").toString());
                        monthxcLReturnEnity.setV_OPERANAME(cmap.get("V_OPERANAME").toString());

                        if (cmap.get("V_ADJUST").toString().equals("1")) {
                            monthxcLReturnEnity.setV_ADJUST("是");
                        } else {
                            monthxcLReturnEnity.setV_ADJUST("否");
                        }

                        Map weekRet = monthRepository.OutWeekGuidByMonth(cmap.get("V_GUID").toString());
                        List weekList = (List) weekRet.get("list");
                        if (weekList.size() > 0) {
                            List<DDWeekReturnEnity> wdata = new ArrayList<>();
                            for (int w = 0; w < weekList.size(); w++) {
                                Map mweek = (Map) weekList.get(w);
                                DDWeekReturnEnity ddWeekReturnEnity = new DDWeekReturnEnity();
                                ddWeekReturnEnity.setV_WEEKGUID(mweek.get("V_OTHERPLAN_GUID").toString());
                                wdata.add(ddWeekReturnEnity);
                            }
                            monthxcLReturnEnity.setWitems(wdata);
                        }

                        Map defectRet = monthRepository.OutDefectGuidByMonth(cmap.get("V_GUID").toString());
                        List defectList = (List) defectRet.get("list");
                        if (defectList.size() > 0) {
                            List<DDDefectReturnEnity> ddata = new ArrayList<>();
                            for (int d = 0; d < defectList.size(); d++) {
                                Map mdefect = (Map) defectList.get(d);
                                DDDefectReturnEnity ddDefectReturnEnity = new DDDefectReturnEnity();
                                ddDefectReturnEnity.setV_DEFECTGUID(mdefect.get("V_DEFECT_GUID").toString());
                                ddata.add(ddDefectReturnEnity);
                            }
                            monthxcLReturnEnity.setDitems(ddata);
                        }

                        lists.add(monthxcLReturnEnity);
                    }
                    ret.setV_INFO("成功！");
                    ret.setV_TYPE("S");
                    ret.setItems(lists);
                    wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "成功", "月计划" + items.getV_YEAR() + "年" + items.getV_MONTH() + "月" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区成功返回" + result.size());
                } else {
                    wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "成功", "月计划" + items.getV_YEAR() + "年" + items.getV_MONTH() + "月" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区成功返回" + result.size());
                }
            } else {
                wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "失败", "月计划" + items.getV_YEAR() + "年" + items.getV_MONTH() + "月" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区失败返回" + result.size());
                ret.setV_INFO("失败！");
                ret.setV_TYPE("E");
            }
        } catch (Exception e) {
            wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "失败", "月计划" + items.getV_YEAR() + "年" + items.getV_MONTH() + "月" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区返回失败返回");
            ret.setV_INFO(e.getMessage());
            ret.setV_TYPE("E");
        }

        return ret;
    }

    @Override
    public WeekxcReturnEnity Weekxc(WeekxcEnity items) {
        WeekxcReturnEnity ret = new WeekxcReturnEnity();
        try {
            ret.setV_YEAR(items.getV_V_YEAR());
            ret.setV_MONTH(items.getV_V_MONTH());
            ret.setV_WEEK(items.getV_V_WEEK());
            ret.setV_DEPTCODE(items.getV_DEPT_CODE());
            ret.setV_ORGCODE(items.getV_ORG_CODE());
            ret.setSYSNAME("SBGL");

            Map result = monthRepository.OutWeekData(items.getV_V_YEAR(), items.getV_V_MONTH(), items.getV_V_WEEK(), items.getV_ORG_CODE(), items.getV_DEPT_CODE());
            if (result.size() > 0) {
                if (result.get("V_INFO").equals("SUCCESS")) {
                    List<WeekxChReturnEnity> lists = new ArrayList<>();
                    List mlist = (List) result.get("list");
                    for (int m = 0; m < mlist.size(); m++) {
                        Map cmap = (Map) mlist.get(m);
                        WeekxChReturnEnity weekxChReturnEnity = new WeekxChReturnEnity();

                        weekxChReturnEnity.setV_GUID(cmap.get("V_GUID").toString());
                        weekxChReturnEnity.setV_EQUTYPECODE(cmap.get("V_EQUTYPECODE").toString());
                        weekxChReturnEnity.setV_EQUCODE(cmap.get("V_EQUCODE").toString());
                        weekxChReturnEnity.setV_REPAIRMAJOR(cmap.get("V_REPAIRMAJOR_CODE").toString());
                        weekxChReturnEnity.setV_CONTENT(cmap.get("V_CONTENT").toString());
                        weekxChReturnEnity.setV_STARTTIME(cmap.get("V_STARTTIME").toString());

                        weekxChReturnEnity.setV_ENDTIME(cmap.get("V_ENDTIME").toString());
                        weekxChReturnEnity.setV_HOUR(cmap.get("V_HOUR").toString());
                        weekxChReturnEnity.setV_INDATE(cmap.get("V_INDATE").toString());
                        weekxChReturnEnity.setV_INPER(cmap.get("V_INPER").toString());
                        weekxChReturnEnity.setV_BZ(cmap.get("V_BZ").toString());
                        weekxChReturnEnity.setV_STATE(cmap.get("V_STATE").toString());

                        weekxChReturnEnity.setV_MAIN_DEFECT(cmap.get("V_MAIN_DEFECT").toString());
                        weekxChReturnEnity.setV_EXPECT_AGE(cmap.get("V_EXPECT_AGE").toString());
                        weekxChReturnEnity.setV_REPAIR_PER(cmap.get("V_REPAIR_PER").toString());
                        weekxChReturnEnity.setV_SGWAYNAME(cmap.get("V_SGWAYNAME").toString());
                        weekxChReturnEnity.setV_FLAG(cmap.get("V_FLAG").toString());
                        weekxChReturnEnity.setV_OPERANAME(cmap.get("V_OPERANAME").toString());
                        weekxChReturnEnity.setV_STR01(cmap.get("V_WORKORDER_GUID").toString());

                        Map workguidRet = monthRepository.OutWorkGuidByWeek(cmap.get("V_GUID").toString());
                        List workList = (List) workguidRet.get("list");
                        if (workList.size() > 0) {
                            List<DWWorkReturnEnity> wkdata = new ArrayList<>();
                            for (int w = 0; w < workList.size(); w++) {
                                Map wwork = (Map) workList.get(w);
                                DWWorkReturnEnity dwworkReturnEnity = new DWWorkReturnEnity();
                                dwworkReturnEnity.setV_WORKGUID(wwork.get("V_WORKORDER_GUID").toString());
                                wkdata.add(dwworkReturnEnity);
                            }
                            weekxChReturnEnity.setWkitems(wkdata);
                        }

                        Map defguidRet = monthRepository.OutDefGuidByWeek(cmap.get("V_GUID").toString());
                        List defList = (List) defguidRet.get("list");
                        if (defList.size() > 0) {
                            List<DWDefReturnEnity> defdata = new ArrayList<>();
                            for (int d = 0; d < defList.size(); d++) {
                                Map wdefect = (Map) defList.get(d);
                                DWDefReturnEnity dwdefReturnEnity = new DWDefReturnEnity();
                                dwdefReturnEnity.setV_DEFGUID(wdefect.get("V_DEFECT_GUID").toString());
                                defdata.add(dwdefReturnEnity);
                            }
                            weekxChReturnEnity.setWditems(defdata);
                        }

                        lists.add(weekxChReturnEnity);
                    }
                    ret.setV_INFO("成功！");
                    ret.setV_TYPE("S");
                    ret.setItems(lists);
                    wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "成功", "周计划" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_WEEK() + "周" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区成功返回" + result.size());
                } else {
                    wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "成功", "周计划" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_WEEK() + "周" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区失败返回" + result.size());
                }
            } else {
                wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "失败", "周计划" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_WEEK() + "周" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区失败返回" + result.size());
                ret.setV_INFO("失败！");
                ret.setV_TYPE("E");
            }
        } catch (Exception e) {
            wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "失败", "周计划" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_WEEK() + "周" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区返回失败返回");
            ret.setV_INFO(e.getMessage());
            ret.setV_TYPE("E");
        }

        return ret;
    }

    @Override
    public WorkOrderRetEnity WorkOrder(WorkOrderInEnity items) {
        WorkOrderRetEnity workOrderRetEnity = new WorkOrderRetEnity();
        workOrderRetEnity.setSYSNAME("SBGL");
        List<WorkOrderRetOEnity> RlistO = new ArrayList<>();
        try {
            Map result = monthRepository.OutWorkOrderData(items.getV_V_YEAR(), items.getV_V_MONTH(), items.getV_V_ORGCODE(), items.getV_V_DEPTCODE());

            if (result.size() > 0) {
                if (result.get("V_INFO").equals("SUCCESS")) {

                    List listw = (List) result.get("list");

                    if (listw.size() > 0) {
                        for (int i = 0; i < listw.size(); i++) {

                            WorkOrderRetOEnity workOrderRetOEnity = new WorkOrderRetOEnity();

                            List<WorkOrderRetTEnity> RlistM = new ArrayList<>();


                            Map mapw = (Map) listw.get(i);
                            workOrderRetOEnity.setV_ORDERGUID(mapw.get("V_ORDERGUID").toString());
                            workOrderRetOEnity.setV_ORDER_TYP_TXT(mapw.get("V_ORDER_TYP_TXT").toString());
                            workOrderRetOEnity.setV_FUNC_LOC(mapw.get("V_FUNC_LOC").toString());
                            workOrderRetOEnity.setV_EQUIP_NO(mapw.get("V_EQUIP_NO").toString());
                            workOrderRetOEnity.setV_EQUIP_NAME(mapw.get("V_EQUIP_NAME").toString());
                            workOrderRetOEnity.setD_START_DATE(mapw.get("D_START_DATE").toString());
                            workOrderRetOEnity.setD_FINISH_DATE(mapw.get("D_FINISH_DATE").toString());
                            workOrderRetOEnity.setV_SHORT_TXT(mapw.get("V_SHORT_TXT").toString());
                            workOrderRetOEnity.setV_WBS(mapw.get("V_WBS").toString());
                            workOrderRetOEnity.setV_WBS_TXT(mapw.get("V_WBS_TXT").toString());
                            workOrderRetOEnity.setV_ENTERED_BY(mapw.get("V_PERSONNAME").toString());
                            workOrderRetOEnity.setD_ENTER_DATE(mapw.get("D_ENTER_DATE").toString());
                            workOrderRetOEnity.setV_ORDER_STATUS(mapw.get("V_STATENAME").toString());
                            workOrderRetOEnity.setV_DEPTNAME(mapw.get("V_DEPTNAME").toString());
                            workOrderRetOEnity.setV_DEPTCODEREPARIRNAME(mapw.get("V_DEPTNAMEREPARIR").toString());
                            workOrderRetOEnity.setV_DEFECTGUID(mapw.get("V_DEFECTGUID").toString());
                            workOrderRetOEnity.setV_STATECODE_STATUS(mapw.get("V_STATENAME").toString());
                            workOrderRetOEnity.setD_FACT_START_DATE(mapw.get("D_FACT_START_DATE").toString());
                            workOrderRetOEnity.setD_FACT_FINISH_DATE(mapw.get("D_FACT_FINISH_DATE").toString());
                            workOrderRetOEnity.setV_STATECODE_STATUS("");
                            workOrderRetOEnity.setV_MONEY(mapw.get("V_SUM_MONEY").toString());

                            Map wmm = monthRepository.OutWorkOrderMMData(mapw.get("V_ORDERGUID").toString());

                            List listm = (List) wmm.get("list");

                            if (listm.size() > 0) {
                                for (int j = 0; j < listm.size(); j++) {
                                    WorkOrderRetTEnity workOrderRetTEnity = new WorkOrderRetTEnity();
                                    Map mapm = (Map) listm.get(j);

                                    workOrderRetTEnity.setBILLCODE(mapm.get("V_FETCHORDERGUID").toString());
                                    workOrderRetTEnity.setVCH_SPAREPART_CODE(mapm.get("V_MATERIALCODE").toString());
                                    workOrderRetTEnity.setVCH_SPAREPART_NAME(mapm.get("V_MATERIALNAME").toString());
                                    workOrderRetTEnity.setVCH_UNIT(mapm.get("V_UNIT").toString());
                                    workOrderRetTEnity.setPRICE(mapm.get("F_UNITPRICE").toString());
                                    workOrderRetTEnity.setF_NUMBER(mapm.get("I_OUTNUMBER").toString());
                                    workOrderRetTEnity.setV_ORDERGUID(mapm.get("V_ORDERGUID").toString());
                                    RlistM.add(workOrderRetTEnity);
                                }
                                workOrderRetOEnity.setItemsMM(RlistM);
                            }

                            Map wdef=monthRepository.OutDefGuidByWork(mapw.get("V_ORDERGUID").toString());
                            List listd=(List) wdef.get("list");
                            if(listd.size()>0){
                                List<WWDefReturnEnity> dlistd = new ArrayList<>();
                                for(int p=0;p<listm.size(); p++){
                                    WWDefReturnEnity wwDefReturnEnity=new WWDefReturnEnity();
                                    Map dmap=(Map) listd.get(p);

                                    wwDefReturnEnity.setV_V_DEFGUID(dmap.get("V_DEFECT_GUID").toString());
                                    dlistd.add(wwDefReturnEnity);
                                }
                                workOrderRetOEnity.setDgitems(dlistd);
                            }
                            RlistO.add(workOrderRetOEnity);
                        }
                    }
                }
                workOrderRetEnity.setItems(RlistO);
                wxjhRepository.WebServiceLog(items.getV_V_SYSTEM(), "", "成功", "工单" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_ORGCODE() + "厂矿" + items.getV_V_DEPTCODE() + "作业区成功返回" + result.size());
            } else {
                wxjhRepository.WebServiceLog(items.getV_V_SYSTEM(), "", "成功", "工单" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_ORGCODE() + "厂矿" + items.getV_V_DEPTCODE() + "作业区失败返回" + result.size());
            }
        } catch (Exception e) {
            wxjhRepository.WebServiceLog(items.getV_V_SYSTEM(), "", "失败", "工单" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_ORGCODE() + "厂矿" + items.getV_V_DEPTCODE() + "作业区返回失败返回");
        }
        return workOrderRetEnity;
    }

    @Override
    public DefectRetEnity Defect(DefectEnity items) {
        DefectRetEnity defectRetEnity = new DefectRetEnity();
        List<DefectRetOEnity> lists = new ArrayList<>();
        try {
            defectRetEnity.setSYSNAME("SBGL");
            defectRetEnity.setV_YEAR(items.getV_V_YEAR());
            defectRetEnity.setV_MONTH(items.getV_V_MONTH());
            defectRetEnity.setV_ORGCODE(items.getV_V_ORGCODE());
            defectRetEnity.setV_DEPTCODE(items.getV_V_DEPTCODE());

            Map result = monthRepository.OutDefectData(items.getV_V_YEAR(), items.getV_V_MONTH(), items.getV_V_ORGCODE(), items.getV_V_DEPTCODE());

            List rlist = (List) result.get("list");

            if (rlist.size() > 0) {
                for (int i = 0; i < rlist.size(); i++) {
                    DefectRetOEnity defectRetOEnity = new DefectRetOEnity();
                    Map dmap = (Map) rlist.get(i);
                    defectRetOEnity.setV_GUID(dmap.get("V_GUID").toString());
                    defectRetOEnity.setV_DEFECTLIST(dmap.get("V_DEFECTLIST").toString());
                    defectRetOEnity.setV_SOURCECODE(dmap.get("V_SOURCENAME").toString());
                    defectRetOEnity.setD_DEFECTDATE(dmap.get("D_DEFECTDATE").toString());
                    defectRetOEnity.setD_INDATE(dmap.get("D_INDATE").toString());
                    defectRetOEnity.setV_PERNAME(dmap.get("V_PERNAME").toString());
                    defectRetOEnity.setV_DEPTNAME(dmap.get("V_DEPTNAME").toString());
                    defectRetOEnity.setV_EQUCODE(dmap.get("V_EQUCODE").toString());
                    defectRetOEnity.setV_STATECODE(dmap.get("V_STATENAME").toString());
                    defectRetOEnity.setV_EQUSITE(dmap.get("V_EQUSITE").toString());
                    defectRetOEnity.setV_INPERNAME(dmap.get("V_INPERNAME").toString());
                    defectRetOEnity.setV_EQUTYPECODE(dmap.get("V_EQUTYPECODE").toString());
                    defectRetOEnity.setV_ORGNAME(dmap.get("V_ORGNAME").toString());
                    defectRetOEnity.setV_HOUR(dmap.get("V_HOUR").toString());
                    defectRetOEnity.setV_IDEA(dmap.get("V_IDEA").toString());
                    defectRetOEnity.setV_BZ(dmap.get("V_BZ").toString());
                    lists.add(defectRetOEnity);
                }
                defectRetEnity.setItems(lists);
                wxjhRepository.WebServiceLog(items.getV_V_SYSTEM(), "", "成功", "缺陷" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_ORGCODE() + "厂矿" + items.getV_V_DEPTCODE() + "作业区成功返回" + result.size());

            } else {
                wxjhRepository.WebServiceLog(items.getV_V_SYSTEM(), "", "失败", "缺陷" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_ORGCODE() + "厂矿" + items.getV_V_DEPTCODE() + "作业区失败返回" + result.size());
            }
        } catch (Exception e) {
            wxjhRepository.WebServiceLog(items.getV_V_SYSTEM(), "", "失败", "缺陷" + items.getV_V_YEAR() + "年" + items.getV_V_MONTH() + "月" + items.getV_V_ORGCODE() + "厂矿" + items.getV_V_DEPTCODE() + "作业区失败返回");
        }
        return defectRetEnity;
    }
}

