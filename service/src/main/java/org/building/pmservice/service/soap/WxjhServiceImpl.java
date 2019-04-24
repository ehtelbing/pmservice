package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.*;
import org.building.pmservice.service.repository.WxjhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.List;

@WebService
@Component
public class WxjhServiceImpl implements WxjhService {

    @Autowired
    private WxjhRepository wxjhRepository;

    @Override
    public void WWQXCLJG(List<WxqxcljgEnity> items) {
        WxqxcljgReturnEnity ret = new WxqxcljgReturnEnity();
        try {
            String result = "";
            if (items.size() > 0) {
                for (int i = 0; i < items.size(); i++) {
                    WxqxcljgEnity w = items.get(i);
                    try {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        result = wxjhRepository.InsertWxProject(w.getV_SYSTEM(), w.getV_GUID(), w.getV_DEFECT_GUID(), w.getV_YEAR(), w.getV_MONTH(),
                                w.getV_ORGCODE(), w.getV_DEPTCODE(), w.getV_PROJECT_CODE(), w.getV_PROJECT_NAME(), w.getV_WBS_CODE(),
                                "", w.getV_CONTENT(), String.valueOf(w.getV_BUDGET_MONEY()), w.getV_BILL_CODE(), w.getV_PROJECT_STATUS(), w.getV_DEFECT_STATUS(), w.getV_REPAIR_DEPT(),
                                w.getV_REPAIR_DEPT_TXT(), w.getV_FZR(), sdf.format(w.getV_DATE_B()), sdf.format(w.getV_DATE_E()),
                                w.getV_INPER(), sdf.format(w.getV_INTIEM()), w.getV_PORJECT_GUID());
                        if (result.equals("SUCCESS")) {
                            wxjhRepository.WebServiceLog(w.getV_SYSTEM(), w.getV_GUID(), "成功", "外委放行计划WebService传递成功，信息插入成功！唯一值为放行计划guid" + w.getV_GUID());
                        } else {
                            wxjhRepository.WebServiceLog(w.getV_SYSTEM(), w.getV_GUID(), "失败", "外委维修计划处理结果下传WebService成功，信息插入失败！唯一值为放行计划guid" + w.getV_GUID());
                        }
                    } catch (Exception e) {
                        wxjhRepository.WebServiceLog(w.getV_SYSTEM(), w.getV_GUID(), "失败", "外委维修计划处理结果下传WebService成功，信息插入失败！失败信息为" + e.getMessage() + "唯一值为放行计划guid" + w.getV_GUID());
                    }
                }
            }
        } catch (Exception e) {
            wxjhRepository.WebServiceLog("", "", "失败", "外委维修计划处理结果下传WebService失败，失败信息为" + e.getMessage());
            ret.setV_INFO(e.getMessage());
            ret.setV_TYPE("E");
        }
        ret.setV_INFO("成功！");
        ret.setV_TYPE("S");
    }


    @Override
    public void WWQXBH(List<WwqxbhEnity> items) {
        WwqxbhReturnEnity ret = new WwqxbhReturnEnity();
        try {
            String result = "";
            if (items.size() > 0) {
                for (int i = 0; i < items.size(); i++) {
                    WwqxbhEnity w = items.get(i);
                    try {

                        result = wxjhRepository.DefectBack(w.getV_DEFECT_GUID(), w.getV_BILL_CODE(), w.getV_DEFECT_TYPE(), w.getV_GUID());
                        if (result.equals("SUCCESS")) {
                            wxjhRepository.WebServiceLog("", w.getV_DEFECT_GUID(), "成功", "外委维修计划缺陷驳回WebService成功，信息插入成功！唯一值为缺陷guid" + w.getV_DEFECT_GUID());
                        } else {
                            wxjhRepository.WebServiceLog("", w.getV_DEFECT_GUID(), "失败", "外委维修计划缺陷驳回WebService成功，信息插入失败！唯一值为缺陷guid" + w.getV_DEFECT_GUID());
                        }
                    } catch (Exception e) {
                        wxjhRepository.WebServiceLog("", w.getV_DEFECT_GUID(), "失败", "外委维修计划缺陷驳回WebService成功，信息插入失败！失败信息为" + e.getMessage() + "唯一值为缺陷guid" + w.getV_DEFECT_GUID());
                    }
                }
            }
        } catch (Exception e) {
            wxjhRepository.WebServiceLog("", "", "失败", "外委维修计划缺陷驳回WebService失败，失败信息为" + e.getMessage());
            ret.setV_INFO(e.getMessage());
            ret.setV_TYPE("E");
        }
        ret.setV_INFO("成功！");
        ret.setV_TYPE("S");
    }

    @Override
    public void Wwjsqxjg(List<WwjsqxjgEnity> items){
        WwjsqxjgReturnEnity ret = new WwjsqxjgReturnEnity();
        try {
            String result = "";
            if (items.size() > 0) {
                for (int i = 0; i < items.size(); i++) {
                    WwjsqxjgEnity w = items.get(i);
                    try {

                        result = wxjhRepository.DefectBack(w.getV_DEFECT_GUID(), w.getV_BILL_CODE(), w.getV_DEFECT_TYPE(), w.getV_GUID());
                        if (result.equals("SUCCESS")) {
                            wxjhRepository.WebServiceLog("", w.getV_DEFECT_GUID(), "成功", "委外竣工（决算）结果上传WebService成功，信息插入成功！唯一值为缺陷guid" + w.getV_DEFECT_GUID());
                        } else {
                            wxjhRepository.WebServiceLog("", w.getV_DEFECT_GUID(), "失败", "委外竣工（决算）结果上传WebService成功，信息插入失败！唯一值为缺陷guid" + w.getV_DEFECT_GUID());
                        }
                    } catch (Exception e) {
                        wxjhRepository.WebServiceLog("", w.getV_DEFECT_GUID(), "失败", "委外竣工（决算）结果上传WebService成功，信息插入失败！失败信息为" + e.getMessage() + "唯一值为缺陷guid" + w.getV_DEFECT_GUID());
                    }
                }
            }
        } catch (Exception e) {
            wxjhRepository.WebServiceLog("", "", "失败", "委外竣工（决算）结果上传WebService失败，失败信息为" + e.getMessage());
            ret.setV_INFO(e.getMessage());
            ret.setV_TYPE("E");
        }
        ret.setV_INFO("成功！");
        ret.setV_TYPE("S");
    }
}
