package org.building.pmservice.service.soap;


import org.building.pmservice.service.Enity.MonthxcEnity;
import org.building.pmservice.service.Enity.MonthxcLReturnEnity;
import org.building.pmservice.service.Enity.MonthxcReturnEnity;
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
            Map result = new HashMap();
            List mlist = new ArrayList();

            result = monthRepository.OutMonthData(items.getV_YEAR(), items.getV_MONTH(), items.getV_ORG_CODE(), items.getV_DEPT_CODE());
            if(result.get("RET").equals("SUCCESS")) {
                if (result.size() > 0) {
                    List<MonthxcLReturnEnity> lists = new ArrayList<>();
                    mlist = (List) result.get("list");
                    for (int m = 0; m < result.size(); m++) {
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

                        monthxcLReturnEnity.setV_ADJUST(cmap.get("").toString());
                        monthxcLReturnEnity.setV_STR01(cmap.get("").toString());
                        monthxcLReturnEnity.setV_STR02(cmap.get("").toString());
                        monthxcLReturnEnity.setV_STR03(cmap.get("").toString());
                        monthxcLReturnEnity.setV_STR04(cmap.get("").toString());
                        monthxcLReturnEnity.setV_STR05(cmap.get("").toString());
                        lists.add(monthxcLReturnEnity);
                    }
                    ret.setItems(lists);
                    wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "成功", "月计划" + items.getV_YEAR() + "年" + items.getV_MONTH() + "月" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区成功返回" + result.size());
                } else {
                    wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "成功", "月计划" + items.getV_YEAR() + "年" + items.getV_MONTH() + "月" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区成功返回" + result.size());
                }
            }else{
                    wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "失败", "月计划" + items.getV_YEAR() + "年" + items.getV_MONTH() + "月" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区失败返回" + result.size());
                ret.setV_INFO("失败！");
                ret.setV_TYPE("E");
            }
        } catch (Exception e) {
            wxjhRepository.WebServiceLog(items.getV_SYSTEM(), "", "失败", "月计划" + items.getV_YEAR() + "年" + items.getV_MONTH() + "月" + items.getV_ORG_CODE() + "厂矿" + items.getV_DEPT_CODE() + "作业区返回失败返回");
            ret.setV_INFO(e.getMessage());
            ret.setV_TYPE("E");
        }
        ret.setV_INFO("成功！");
        ret.setV_TYPE("S");
        return ret;
    }
}

