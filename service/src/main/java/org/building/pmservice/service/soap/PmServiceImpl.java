package org.building.pmservice.service.soap;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.building.pmservice.service.Enity.DbslEnity;
import org.building.pmservice.service.Enity.DbslReturnEnity;
import org.building.pmservice.service.repository.PmRepository;
import org.building.pmservice.service.repository.WxjhRepository;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebService
@Component
public class PmServiceImpl implements PmService {

    @Autowired
    private PmRepository pmRepository;
    @Autowired
    private WxjhRepository wxjhRepository;


    @Value("${pm.url}")
    private String pmurl;

    @Override
    public String getYearPlanList(String V_GUID) {
        List<Map<String, Object>> list = pmRepository.getYearPlanList(V_GUID);
        return "success";
    }

    @Override
    public String SetMonthPlanList(String V_GUID) {
        List<Map<String, Object>> list = pmRepository.SetMonthPlanList(V_GUID);
        return "success";
    }

    @Override
    public DbslReturnEnity DBSL(DbslEnity dbslEnity) {

        DbslReturnEnity dbslReturnEnity = new DbslReturnEnity();
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

            String address = pmurl;

            Client client = dcf.createClient(pmurl + "/services/ActivitiWebController?wsdl");


            Object[] response = client.invoke("GetTaskDbNumByUserid", dbslEnity.getV_USERCODE().toString());

            Document doc = DocumentHelper.parseText(response[0].toString());
            Element rootElt = doc.getRootElement();
            List<Element> childElements = rootElt.elements();

            Map<String,Object> mapEle = new HashMap<String, Object>();
            mapEle = getAllElements(childElements,mapEle);
            dbslReturnEnity.setN_COUNT(Integer.valueOf(mapEle.get("N_COUNT").toString()));
            dbslReturnEnity.setV_SYSTEM("AKSB");
            dbslReturnEnity.setV_URL(pmurl + "/app/pm/page/PM_2103/index.html");
            dbslReturnEnity.setV_USERCODE(dbslEnity.getV_USERCODE());
            wxjhRepository.WebServiceLog("AKSB", "", "成功！", "获取待办任务数量接口成功" );
        } catch (Exception e) {
            wxjhRepository.WebServiceLog("AKSB", "", "失败！", "获取待办任务数量接口失败，失败信息为" + e.getMessage());
            e.printStackTrace();
        }

        return dbslReturnEnity;
    }

    private Map<String, Object> getAllElements(List<Element> childElements,Map<String,Object> mapEle) {
        for (Element ele : childElements) {
            mapEle.put(ele.getName(), ele.getText());
            if(ele.elements().size()>0){
                mapEle = getAllElements(ele.elements(), mapEle);
            }
        }
        return mapEle;
    }

}
