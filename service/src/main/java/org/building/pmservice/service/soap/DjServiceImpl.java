package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.*;
import org.building.pmservice.service.repository.DjRepository;
import org.building.pmservice.service.repository.WorkOrderUpFalgRepository;
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
public class DjServiceImpl implements DjService {

    @Autowired
    private DjRepository djRepository;

    @Autowired
    private WxjhRepository wxjhRepository;

    @Autowired
    private WorkOrderUpFalgRepository workOrderUpFalgRepository;

    @Override
    public List<DjqxReturnEnity> DJQX(DjqxEnity djqxEnity) {

        List<QxEnity> list = djqxEnity.getItems();
        List<DjqxReturnEnity> ret = new ArrayList<DjqxReturnEnity>();
        Map result = new HashMap();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                DjqxReturnEnity r = new DjqxReturnEnity();
                QxEnity qxEnity = list.get(i);
                try {
                    result = djRepository.ImportDefect(djqxEnity.getV_SYSTEM(), qxEnity.getI_ID(), qxEnity.getV_DEFECTLIST(), qxEnity.getV_DEFTYPE(), qxEnity.getV_SOURCEID(), qxEnity.getD_DEFECTDATE(), qxEnity.getD_INDATE(),
                            qxEnity.getV_PERCODE(), qxEnity.getV_PERNAME(), qxEnity.getV_DEPTCODE(), qxEnity.getV_EQUCODE(), qxEnity.getV_IDEA(), qxEnity.getV_EQUSITE(), qxEnity.getV_EQUCHILDCODE(),
                            qxEnity.getV_INPERCODE(), qxEnity.getV_INPERNAME(), qxEnity.getV_EQUTYPECODE(), qxEnity.getV_ORGCODE(), qxEnity.getV_BZ());
                    if (result.get("ret").equals("SUCCESS")) {
                        r.setV_GUID(qxEnity.getI_ID());
                        r.setV_INFO("缺陷信息上传成功");
                        r.setV_TYPE("S");
                        wxjhRepository.WebServiceLog(djqxEnity.getV_SYSTEM(), qxEnity.getI_ID(), "成功", "点检缺陷信息上传WebService成功，信息插入成功！唯一值为缺陷ID" + qxEnity.getI_ID() + qxEnity.getV_SOURCEID());
                    } else {
                        wxjhRepository.WebServiceLog(djqxEnity.getV_SYSTEM(), qxEnity.getI_ID(), "失败", "点检缺陷信息上传WebService成功，信息插入失败！唯一值为缺陷ID" + qxEnity.getI_ID() + qxEnity.getV_SOURCEID());
                        r.setV_GUID(qxEnity.getI_ID());
                        r.setV_INFO(result.get("ret").toString());
                        r.setV_TYPE("E");
                    }
                } catch (Exception e) {
                    wxjhRepository.WebServiceLog(djqxEnity.getV_SYSTEM(), qxEnity.getI_ID(), "失败", "点检缺陷信息上传WebService成功，信息插入失败！失败信息为" + e.getMessage() + "唯一值为缺陷ID" + qxEnity.getI_ID() + qxEnity.getV_SOURCEID());
                    r.setV_GUID(qxEnity.getI_ID());
                    r.setV_INFO("缺陷信息上传失败,失败信息为" + e.getMessage());
                    r.setV_TYPE("E");
                }
                ret.add(r);
            }
        }
        return ret;
    }

    @Override
    public void SpotCheckProjRet(SpotCheckProjEnity spotCheckProjEnity) {
        try {
            Map result = djRepository.ImportSapWorkOrder(spotCheckProjEnity.getORDER_NO3D(), spotCheckProjEnity.getORDER_NOSAP(), spotCheckProjEnity.getMSG_TYPE(), spotCheckProjEnity.getRET_MSG());
            if (result.get("ret").equals("SUCCESS")) {
                wxjhRepository.WebServiceLog("", spotCheckProjEnity.getORDER_NO3D(), "成功", "点检待修项目上传接口返回消息WebService成功");
            } else {
                wxjhRepository.WebServiceLog("", spotCheckProjEnity.getORDER_NO3D(), "成功", "点检待修项目上传接口返回消息WebService成功");
            }

        } catch (Exception e) {
            wxjhRepository.WebServiceLog("", spotCheckProjEnity.getORDER_NO3D(), "失败", "点检待修项目上传接口返回消息WebService失败");
        }
    }

    @Override
    public String PM0002(String ORDER_NO, String STATUS, String BUSINESS_TYPE) {
        try {

            String result = workOrderUpFalgRepository.PRO_PM_WORKORDER_SAP_UPFALG(ORDER_NO, STATUS, BUSINESS_TYPE, "");
            if (result.equals("success")) {
                wxjhRepository.WebServiceLog("", ORDER_NO, "成功", "人员对应虚拟岗关系下传成功");

            } else {
                wxjhRepository.WebServiceLog("", ORDER_NO, "失败", "人员对应虚拟岗关系下传成功");
                return "fail";
            }

        } catch (Exception e) {
            wxjhRepository.WebServiceLog("", ORDER_NO, "失败", "人员对应虚拟岗关系下传失败" + e.getMessage());
            return "fail";
        }
        return "success";
    }

    @Override
    public void StockIssue(String MATDOC_NOSAP, String MJAHR, String DOC_DATE, String PSTNG_DATE, String HEARDER_TXT, String SYSNAME, List<linesOfItems> list) {

        try {
            if (list.size() > 0) {
                String old = "";
                String news = "";
                for (int i = 0; i < list.size(); i++) {

                    linesOfItems w = list.get(i);
                    try {
                        Map map = workOrderUpFalgRepository.PRO_SAP_WORKORDER_GET(w.getPROD_NO());
                        if (i == 0) {
                            old = map.get("V_V_ORDERGUID").toString();
                            news = map.get("V_V_ORDERGUID").toString();
                        } else if (i > 0) {
                            news = map.get("V_V_ORDERGUID").toString();
                        }
                        String result = workOrderUpFalgRepository.PRO_PM_WORKORDER_SPARE_MM_SET(map.get("V_V_ORDERGUID").toString(), map.get("V_V_ORDERID").toString(), w.getITEM_NOSAP(), w.getMATERIAL(), w.getMATERIAL(), "",
                                "", "0", w.getENTRY_QNT(), "out");

                        if (result.equals("成功")) {
                            wxjhRepository.WebServiceLog("", map.get("V_V_ORDERGUID").toString(), "成功", "发货下传成功，信息插入成功！唯一值guid" + map.get("V_V_ORDERGUID").toString());
                        } else {
                            wxjhRepository.WebServiceLog("", map.get("V_V_ORDERGUID").toString(), "失败", "发货下传成功，信息插入失败！唯一值guid" + map.get("V_V_ORDERGUID").toString());
                        }
                        if (old != news) {
                            workOrderUpFalgRepository.PRO_PM_WORKORDER_SPARE_UPDATE(old);
                            old = news;
                        }
                    } catch (Exception e) {
                        wxjhRepository.WebServiceLog("", news, "失败", "发货下传失败，信息插入失败！失败信息为" + e.getMessage() + "唯一值guid" + news);
                    }
                }
                String ret = workOrderUpFalgRepository.PRO_PM_WORKORDER_SPARE_UPDATE(news);
            }
        } catch (Exception e) {
            wxjhRepository.WebServiceLog("", "", "失败", "发货下传失败，失败信息为" + e.getMessage());

        }

    }
}
