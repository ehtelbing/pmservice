package org.building.pmservice.service.soap;

import org.building.pmservice.service.repository.PmRepository;
import org.building.pmservice.service.wclient.DJQXCLJG.DTDJQXCLJG;
import org.building.pmservice.service.wclient.DJQXCLJG.DTDJQXCLJGRet;
import org.building.pmservice.service.wclient.DJQXCLJG.SIDJQXCLJGOutSyn;
import org.building.pmservice.service.wclient.DJQXCLJG.SIDJQXCLJGOutSynService;
import org.building.pmservice.service.wclient.JXWCJG.DTJXWCJG;
import org.building.pmservice.service.wclient.JXWCJG.DTJXWCJGRet;
import org.building.pmservice.service.wclient.JXWCJG.SIJXWCJGOutSyn;
import org.building.pmservice.service.wclient.JXWCJG.SIJXWCJGOutSynService;
import org.building.pmservice.service.wclient.RYQX.DTRYQX;
import org.building.pmservice.service.wclient.RYQX.DTRYQXRet;
import org.building.pmservice.service.wclient.RYQX.SIRYQXOutSyn;
import org.building.pmservice.service.wclient.RYQX.SIRYQXOutSynService;
import org.building.pmservice.service.wclient.SpotChkProj.DTSPOTCHECKPROJ;
import org.building.pmservice.service.wclient.SpotChkProj.SISpotChkProjIn;
import org.building.pmservice.service.wclient.SpotChkProj.SISpotChkProjInService;
import org.building.pmservice.service.wclient.WWQX.DTWWQX;
import org.building.pmservice.service.wclient.WWQX.DTWWQXRet;
import org.building.pmservice.service.wclient.WWQX.SIWWQXOutSyn;
import org.building.pmservice.service.wclient.WWQX.SIWWQXOutSynService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService
@Component
public class PmServiceImpl implements PmService {

    @Autowired
    private PmRepository pmRepository;

    @Override
    public String PM0011(String xml) {
        Map result = new HashMap();
        Document root = DocumentHelper.createDocument();
        Element WriteDataRequest = root.addElement("RetItems");
        Map<String, Object> mapEle = new HashMap<String, Object>();

        try {
            Document doc = DocumentHelper.parseText(xml);
            Element rootElt = doc.getRootElement();
            List<Element> childElements = rootElt.elements();

            mapEle = getAllElements(childElements, mapEle);

            Map retMap = pmRepository.PM_DEFECT_LOG_FROMPRO_DEFECT_S(mapEle.get("XML").toString());
            String DefectPicUrl = mapEle.get("Url").toString();
            String ProjectUrl = mapEle.get("ProjectUrl").toString();

            DTWWQX dtwwqx = new DTWWQX();
            List<DTWWQX.Items> dlist = new ArrayList<DTWWQX.Items>();

            List list = (List) retMap.get("list");
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                DTWWQX.Items items = new DTWWQX.Items();
                items.setVBILLADD(ProjectUrl + map.get("V_BILL_CODE").toString());
                items.setVBILLCODE(map.get("V_BILL_CODE").toString());
                items.setVCPZL(map.get("V_CPZL").toString());
                items.setVDEFECTDATE(map.get("V_DEFECTDATE").toString());
                items.setVDEFECTGUID(map.get("V_DEFECTGUID").toString());
                items.setVDEFECTLIST(map.get("V_DEFECTLIST").toString());
                items.setVDEFECTPERCODE(map.get("V_DEFECTPERCODE").toString());
                items.setVDEFECTSOL(map.get("V_DEFECT_SOL").toString());
                items.setVDEPTCODE(map.get("V_DEPTCODE").toString());
                items.setVEQUMAIN(map.get("V_EQU_MAIN").toString());
                items.setVEQUMAINCODE(map.get("V_EQU_MAIN_CODE").toString());
                items.setVEQUNAME(map.get("V_EQU_NAME").toString());
                items.setVEUQCODE(map.get("V_EUQ_CODE").toString());
                items.setVFILES(DefectPicUrl + map.get("V_DEFECTGUID").toString());
                items.setVJHLB(map.get("V_JHLB").toString());
                items.setVMEMO(map.get("V_MEMO").toString());
                items.setVMONTH(map.get("V_MONTH").toString());
                items.setVORGCODE(map.get("V_ORGCODE").toString());
                items.setVSCLB(map.get("V_SCLB").toString());
                items.setVSPECIALTY(map.get("V_SPECIALTY").toString());
                items.setVSTR01("");
                items.setVSTR02("");
                items.setVSTR03("");
                items.setVSTR04("");
                items.setVSTR05("");
                items.setVSYSTEM(map.get("V_SYSTEM").toString());
                items.setVYEAR(map.get("V_YEAR").toString());
                dlist.add(items);
            }

            dtwwqx.setItems(dlist);
            URL url = new URL("file:" + mapEle.get("WsdlUrl").toString());
            QName name = new QName("http://www.anshanmining.com/EAM_PM/", "SI_WWQX_Out_SynService");
            SIWWQXOutSynService siwwqxOutSynService = new SIWWQXOutSynService(url, name);
            SIWWQXOutSyn soap = siwwqxOutSynService.getSIWWQXOutSynPort();

            BindingProvider bp = (BindingProvider) soap;
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, mapEle.get("piusername").toString());//用户名
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, mapEle.get("pipassword").toString());
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, mapEle.get("Pm0011Url").toString());

            DTWWQXRet ret = soap.siWWQX(dtwwqx);

            if (ret.getVTYPE().equals("S")) {
                result = pmRepository.WebServiceLog("AKSB", mapEle.get("XML").toString(), "成功", "委外缺陷信息下传WebService：SI_WWQX_Out_Syn_PM0011成功，信息插入成功！唯一值为维修计划申请guid" + mapEle.get("XML").toString() + "接口返回信息数量为：" + dtwwqx.getItems().size());

                WriteDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
                WriteDataRequest.addElement("type").setText(ret.getVTYPE());
                WriteDataRequest.addElement("info").setText(ret.getVINFO());

            } else {
                result = pmRepository.WebServiceLog("AKSB", mapEle.get("XML").toString(), "失败", "委外缺陷信息下传WebService：SI_WWQX_Out_Syn_PM0011失败，信息插入成功！唯一值为维修计划申请guid" + mapEle.get("XML").toString() + "接口返回信息数量为：" + dtwwqx.getItems().size());

                WriteDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
                WriteDataRequest.addElement("type").setText(ret.getVTYPE());
                WriteDataRequest.addElement("info").setText(ret.getVINFO());
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = pmRepository.WebServiceLog("AKSB", mapEle.get("XML").toString(), "失败", "委外缺陷信息下传WebService：SI_WWQX_Out_Syn_PM0011失败，信息插入成功！唯一值为维修计划申请guid" + mapEle.get("XML").toString() + "错误信息为：" + e.getMessage());

            WriteDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
            WriteDataRequest.addElement("type").setText("E");
            WriteDataRequest.addElement("info").setText(e.getMessage());
        }
        return root.asXML();
    }

    @Override
    public String PM0014(String clientXml) {
        Map result = new HashMap();
        Document root = DocumentHelper.createDocument();
        Element WriteDataRequest = root.addElement("RetItems");
        Map<String, Object> mapEle = new HashMap<String, Object>();
        try {
            Document doc = DocumentHelper.parseText(clientXml);
            Element rootElt = doc.getRootElement();
            List<Element> childElements = rootElt.elements();

            mapEle = getAllElements(childElements, mapEle);

            DTJXWCJG dtjxwcjg = new DTJXWCJG();
            List<DTJXWCJG.Items> list = new ArrayList<DTJXWCJG.Items>();
            DTJXWCJG.Items items = new DTJXWCJG.Items();

            Map retmap = pmRepository.maintain_by_defect_sel_dguid(mapEle.get("V_DEFECT_GUID").toString());
            List rlist = (List) retmap.get("list");

            Map emap = (Map) rlist.get(0);

            items.setVDEFECTGUID(mapEle.get("V_DEFECT_GUID").toString());
            items.setVDEFECTTYPE(mapEle.get("V_DEFECT_TYPE").toString());
            items.setVSYSTEM(mapEle.get("V_SYSTEM").toString());
            items.setVGUID(emap.get("FX_GUID").toString());
            list.add(items);

            dtjxwcjg.setItems(list);

            URL url = new URL("file:" + mapEle.get("WsdlUrl").toString());
            QName name = new QName("http://www.anshanmining.com/EAM_PM/", "SI_JXWCJG_Out_SynService");
            SIJXWCJGOutSynService sijxwcjgOutSynService = new SIJXWCJGOutSynService(url, name);
            SIJXWCJGOutSyn soap = sijxwcjgOutSynService.getSIJXWCJGOutSynPort();


            BindingProvider bp = (BindingProvider) soap;
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, mapEle.get("piusername").toString());
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, mapEle.get("pipassword").toString());
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, mapEle.get("Pm0014Url").toString());

            DTJXWCJGRet ret = soap.siJXWCJG(dtjxwcjg);

            if (ret.getVTYPE().equals("S")) {
                result = pmRepository.WebServiceLog(mapEle.get("V_SYSTEM").toString(), mapEle.get("V_DEFECT_GUID").toString(), "成功", "检修完成结果下传WebService：SI_JXWCJG_Out_Syn_PM0014成功，信息插入成功！唯一值为缺陷guid" + mapEle.get("V_DEFECT_GUID").toString() + "接口返回信息为：" + ret.getVINFO());

                WriteDataRequest.addElement("type").setText(ret.getVTYPE());
                WriteDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
                WriteDataRequest.addElement("info").setText(ret.getVINFO());
            } else {
                result = pmRepository.WebServiceLog(mapEle.get("V_SYSTEM").toString(), mapEle.get("V_DEFECT_GUID").toString(), "失败", "检修完成结果下传WebService：SI_JXWCJG_Out_Syn_PM0014失败，信息插入成功！唯一值为缺陷guid" + mapEle.get("V_DEFECT_GUID").toString() + "接口返回信息为：" + ret.getVINFO());

                WriteDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
                WriteDataRequest.addElement("type").setText(ret.getVTYPE());
                WriteDataRequest.addElement("info").setText(ret.getVINFO());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = pmRepository.WebServiceLog(mapEle.get("V_SYSTEM").toString(), mapEle.get("V_DEFECT_GUID").toString(), "失败", "检修完成结果下传WebService：SI_JXWCJG_Out_Syn_PM0014失败，信息插入成功！唯一值为缺陷guid" + mapEle.get("V_DEFECT_GUID").toString() + "错误信息为：" + e.getMessage());

            WriteDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
            WriteDataRequest.addElement("type").setText("E");
            WriteDataRequest.addElement("info").setText(e.getMessage());
        }
        return root.asXML();
    }

    @Override
    public String PM0010(String clientXml) {
        Map result = new HashMap();
        Document root = DocumentHelper.createDocument();
        Element WriteDataRequest = root.addElement("RetItems");
        Map<String, Object> mapEle = new HashMap<String, Object>();
        String V_V_SYSTEM = "";
        try {
            Document doc = DocumentHelper.parseText(clientXml);
            Element rootElt = doc.getRootElement();
            List<Element> childElements = rootElt.elements();

            mapEle = getAllElements(childElements, mapEle);

            DTDJQXCLJG dtdjqxcljg = new DTDJQXCLJG();
            List<DTDJQXCLJG.ITEMS> list = dtdjqxcljg.getITEMS();

            Map map = pmRepository.PRO_PM_DEFECT_SEL_GUID(mapEle.get("GUID").toString());

            List mlist = (List) map.get("list");

            for (int i = 0; i < mlist.size(); i++) {
                Map lmap = (Map) mlist.get(i);
                DTDJQXCLJG.ITEMS items = new DTDJQXCLJG.ITEMS();
                V_V_SYSTEM = lmap.get("V_SYSTEM").toString();
                dtdjqxcljg.setVSYSTEM(lmap.get("V_SYSTEM").toString());

                items.setIID(lmap.get("V_SOURCEID").toString());
                if (lmap.get("V_STATECODE").toString().equals("30") || lmap.get("V_STATECODE").toString().equals("23")) {
                    items.setVSTATE("1");
                } else {
                    items.setVSTATE("0");
                }
                items.setVREMARKS(lmap.get("V_BZ").toString());
                items.setVSTR01("");
                items.setVSTR02("");
                items.setVSTR03("");
                items.setVSTR04("");
                items.setVSTR05("");
                list.add(items);
            }
            dtdjqxcljg.setItems(list);

            URL url = new URL("file:" + mapEle.get("WsdlUrl").toString());
            QName name = new QName("http://www.anshanmining.com/EAM_PM/", "SI_DJQXCLJG_Out_SynService");
            SIDJQXCLJGOutSynService sidjqxcljgOutSynService = new SIDJQXCLJGOutSynService(url, name);
            SIDJQXCLJGOutSyn soap = sidjqxcljgOutSynService.getSIDJQXCLJGOutSynPort();


            BindingProvider bp = (BindingProvider) soap;
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, mapEle.get("piusername").toString());
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, mapEle.get("pipassword").toString());
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, mapEle.get("Pm0010Url").toString());
            System.out.println("-------------------系统名称--------------------");

            System.out.println(dtdjqxcljg.getVSYSTEM());
            DTDJQXCLJGRet ret = soap.siDJQXCLJG(dtdjqxcljg);

            List<DTDJQXCLJGRet.ITEMS> retList = ret.getITEMS();

            for (int j = 0; j < retList.size(); j++) {
                DTDJQXCLJGRet.ITEMS retitems = retList.get(j);
                Element PackName = WriteDataRequest.addElement("items");
                if (retitems.getVTYPE().equals("S")) {
                    result = pmRepository.WebServiceLog(V_V_SYSTEM, retitems.getVGUID(), "成功", "点检缺陷处理结果反馈WebService：SI_DJQXCLJG_Out_Syn_PM0010成功，信息插入成功！唯一值为缺陷guid" + mapEle.get("GUID").toString() + "接口返回信息为：" + retitems.getVINFO());
                    PackName.addElement("type").setText(retitems.getVTYPE());
                    PackName.addElement("V_INFO").setText(result.get("V_INFO").toString());
                    PackName.addElement("info").setText(retitems.getVINFO());
                } else {
                    result = pmRepository.WebServiceLog(V_V_SYSTEM, retitems.getVGUID(), "失败", "点检缺陷处理结果反馈WebService：SI_DJQXCLJG_Out_Syn_PM0010失败，信息插入成功！唯一值为缺陷guid" + mapEle.get("GUID").toString() + "接口返回信息为：" + retitems.getVINFO());
                    PackName.addElement("type").setText(retitems.getVTYPE());
                    PackName.addElement("V_INFO").setText(result.get("V_INFO").toString());
                    PackName.addElement("info").setText(retitems.getVINFO());
                }
            }

        } catch (Exception e) {
            Element PackName = WriteDataRequest.addElement("items");
            e.printStackTrace();
            result = pmRepository.WebServiceLog(V_V_SYSTEM, mapEle.get("GUID").toString(), "失败", "点检缺陷处理结果反馈WebService：SI_DJQXCLJG_Out_Syn_PM0010失败，信息插入成功！唯一值为缺陷guid" + mapEle.get("GUID").toString() + "错误信息为：" + e.getMessage());

            PackName.addElement("type").setText("E");
            PackName.addElement("V_INFO").setText(result.get("V_INFO").toString());
            PackName.addElement("info").setText(e.getMessage());
        }
        return root.asXML();
    }

    //PMPERPOW
    @Override
    public String PR1003(String clientXml) {
        Map result = new HashMap();
        Document root = DocumentHelper.createDocument();
        Element writeDataRequest = root.addElement("RETItems");
        Map<String, Object> mapEle = new HashMap<String, Object>();
        String V_TEXT = null;
        try {
            Document doc = DocumentHelper.parseText(clientXml);
            Element rootElt = doc.getRootElement();
            List<Element> childElements = rootElt.elements();

            mapEle = getAllElements(childElements, mapEle);

            V_TEXT = mapEle.get("ROLECODE").toString() + ";" + mapEle.get("ORG").toString();
            Map retmap = pmRepository.PRO_PM_PERCODE_SEL_POWER(mapEle.get("ROLECODE").toString(), mapEle.get("ORG").toString());
            List rlist = (List) retmap.get("list");
            List mlist = (List) retmap.get("mlist");

            for (int i = 0; i < rlist.size(); i++) {

                List<DTRYQX.Items> list = new ArrayList<DTRYQX.Items>();
                Map rmap = (Map) rlist.get(i);
                DTRYQX dtryqx = new DTRYQX();
                dtryqx.setVSYSTEM(mapEle.get("SYSTEM").toString());
                dtryqx.setVUSERCODE(rmap.get("USERCODE").toString());
                dtryqx.setVUSERNAME(rmap.get("USERNAME").toString());

                for (int j = 0; j < mlist.size(); j++) {
                    Map mmap = (Map) mlist.get(j);
                    DTRYQX.Items items = new DTRYQX.Items();
                    items.setVORG(rmap.get("DEPTNEWCODE").toString());
                    items.setVPOST("");
                    items.setVROLE(mapEle.get("ROLECODE").toString());
                    items.setVMENUID(mmap.get("V_MENUCODE").toString());
                    items.setVMENUDESC(mmap.get("V_MENUNAME").toString());
                    items.setVUPMENUD(mmap.get("V_MENUCODE_UP").toString());
                    items.setVIP(mapEle.get("V_IP").toString());
                    items.setVPORT("");
                    if (mmap.get("V_URL").toString().equals("")) {
                        items.setVURL("");
                    } else if (mmap.get("V_URL").toString().indexOf("http") == -1) {
                        items.setVURL(mapEle.get("V_URL").toString() + mmap.get("V_URL").toString());
                    } else {
                        items.setVURL(mmap.get("V_URL").toString());
                    }

                    list.add(items);
                }
                dtryqx.setItems(list);

                System.out.println("--------------------打印开始---------------------");
                System.out.println(dtryqx.getVSYSTEM());
                System.out.println(dtryqx.getVUSERCODE());
                System.out.println(dtryqx.getVUSERNAME());
                System.out.println(list.get(0).getVIP());
                System.out.println(list.get(0).getVMENUDESC());
                System.out.println(list.get(0).getVMENUID());
                System.out.println(list.get(0).getVORG());
                System.out.println(list.get(0).getVPORT());
                System.out.println(list.get(0).getVPOST());
                System.out.println(list.get(0).getVUPMENUD());
                System.out.println(list.get(0).getVURL());
                System.out.println("--------------------打印结束---------------------");

                URL url = new URL("file:" + mapEle.get("WsdlUrl").toString());
                QName name = new QName("http://www.anshanmining.com/use_check/", "SI_RYQX_Out_SynService");
                SIRYQXOutSynService siryqxOutSynService = new SIRYQXOutSynService(url, name);
                SIRYQXOutSyn soap = siryqxOutSynService.getSIRYQXOutSynPort();


                BindingProvider bp = (BindingProvider) soap;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, mapEle.get("piusername").toString());
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, mapEle.get("pipassword").toString());
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, mapEle.get("PR1003Url").toString());
                DTRYQXRet ret = soap.siRYQX(dtryqx);

                if (ret.getVTYPE().equals("S")) {
                    result = pmRepository.WebServiceLog(mapEle.get("SYSTEM").toString(), ret.getVUSERCODE(), "成功", "人员权限上传WebService：SI_RYQX_Out_Syn_PR1003成功，信息插入成功！员工号为" + ret.getVUSERCODE() + "接口返回信息为：" + ret.getVINFO());

                    writeDataRequest.addElement("type").setText(ret.getVTYPE());
                    writeDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
                    writeDataRequest.addElement("info").setText(ret.getVINFO());
                } else {
                    result = pmRepository.WebServiceLog(mapEle.get("SYSTEM").toString(), ret.getVUSERCODE(), "失败", "人员权限上传WebService：SI_RYQX_Out_Syn_PR1003失败，信息插入成功！员工号为" + ret.getVUSERCODE() + "接口返回信息为：" + ret.getVINFO());

                    writeDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
                    writeDataRequest.addElement("type").setText(ret.getVTYPE());
                    writeDataRequest.addElement("info").setText(ret.getVINFO());
                }
            }
        } catch (Exception de) {
            Element PackName = writeDataRequest.addElement("items");
            de.printStackTrace();
            result = pmRepository.WebServiceLog(mapEle.get("SYSTEM").toString(), V_TEXT, "失败", "人员权限上传WebService：SI_RYQX_Out_Syn_PR1003失败，信息插入成功！角色+厂矿" + V_TEXT + "错误信息为：" + de.getMessage());

            PackName.addElement("type").setText("E");
            PackName.addElement("V_INFO").setText(result.get("V_INFO").toString());
            PackName.addElement("info").setText(de.getMessage());
        }
        return root.asXML();

    }

    @Override
    public String PM1001(String clientXml) {
        Map result = new HashMap();
        Document root = DocumentHelper.createDocument();
        Element writeDataRequest = root.addElement("RETItems");
        Map<String, Object> mapEle = new HashMap<String, Object>();

        DTSPOTCHECKPROJ dtspotcheckproj = new DTSPOTCHECKPROJ();
        try {
            Document doc = DocumentHelper.parseText(clientXml);
            Element rootElt = doc.getRootElement();
            List<Element> childElements = rootElt.elements();

            mapEle = getAllElements(childElements, mapEle);

            Map map = pmRepository.PRO_PM_WORKORDER_GET(mapEle.get("ORDERGUID").toString());

            List mlist = (List) map.get("list");

            if (mlist.size() > 0) {
                Map lmap = (Map) mlist.get(0);
                dtspotcheckproj.setORDERNO3D(lmap.get("V_ORDERID").toString());//3级工单号
                dtspotcheckproj.setORDERTYP("AK05");//工单类型（固定）
                dtspotcheckproj.setFUNCLOC(lmap.get("V_FUNC_LOC").toString());//功能位置
                dtspotcheckproj.setEQUIPNO(lmap.get("V_EQUIP_NO").toString());//设备
                dtspotcheckproj.setPLANT(lmap.get("V_PLANT").toString());//维修工厂
                dtspotcheckproj.setIWERK(lmap.get("V_IWERK").toString());//计划工厂
                dtspotcheckproj.setSTARTDATE(lmap.get("D_START_DATE_SAP").toString());//基本开始日期
                dtspotcheckproj.setFINISHDATE(lmap.get("D_FINISH_DATE_SAP").toString());//基本完成日期
                dtspotcheckproj.setACTTYPE("PM01");//PM作业类型
                dtspotcheckproj.setPLANNER(lmap.get("V_PLANNER").toString());//计划员组
                dtspotcheckproj.setWORKCTR(lmap.get("V_WORK_CTR").toString());//维护工作中心
                dtspotcheckproj.setSHORTTXT("AK05");//工单描述
                dtspotcheckproj.setGSBER(lmap.get("V_GSBER").toString());//业务范围

                DTSPOTCHECKPROJ.WBS wbs = new DTSPOTCHECKPROJ.WBS();

                wbs.setValue(lmap.get("V_WBS").toString());

                JAXBElement<DTSPOTCHECKPROJ.WBS> w = new JAXBElement<DTSPOTCHECKPROJ.WBS>(new QName("", "WBS"), DTSPOTCHECKPROJ.WBS.class, DTSPOTCHECKPROJ.class, wbs);

                dtspotcheckproj.setWBS(w);//WBS元素

                URL url = new URL("file:" + mapEle.get("WsdlUrl").toString());
                QName name = new QName("http://www.anshanmining.com/pm/", "SI_SpotChkProj_inService");
                SISpotChkProjInService siSpotChkProjInService = new SISpotChkProjInService(url, name);
                SISpotChkProjIn soap = siSpotChkProjInService.getSISpotChkProjInPort();

                BindingProvider bp = (BindingProvider) soap;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, mapEle.get("piusername").toString());
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, mapEle.get("pipassword").toString());
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, mapEle.get("Pm1001Url").toString());

                soap.siSpotChkProj(dtspotcheckproj);

                result = pmRepository.WebServiceLog("", mapEle.get("ORDERGUID").toString(), "成功", "Sap工单接口WebService：SI_SpotChkProj_inPM1001插入成功，信息插入成功！唯一值为工单guid" + mapEle.get("ORDERGUID").toString());
                writeDataRequest.addElement("type").setText("S");
                writeDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
                writeDataRequest.addElement("info").setText("成功");
            }
        } catch (Exception de) {
            result = pmRepository.WebServiceLog("", mapEle.get("ORDERGUID").toString(), "失败", "Sap工单接口WebService：SI_SpotChkProj_inPM1001插入成功，信息插入失败！唯一值为工单guid" + mapEle.get("ORDERGUID").toString());
            writeDataRequest.addElement("type").setText("S");
            writeDataRequest.addElement("V_INFO").setText(result.get("V_INFO").toString());
            writeDataRequest.addElement("info").setText("失败");
        }
        return root.asXML();

    }

    private Map<String, Object> getAllElements(List<Element> childElements, Map<String, Object> mapEle) {
        for (Element ele : childElements) {
            mapEle.put(ele.getName(), ele.getText());
            if (ele.elements().size() > 0) {
                mapEle = getAllElements(ele.elements(), mapEle);
            }
        }
        return mapEle;
    }

}
