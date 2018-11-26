package org.building.pmservice.service.soap;

import org.building.pmservice.service.enity.LoginInforData;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

//设备系统接口
@WebService
public interface PmService {
    @WebMethod
    public String getYearPlanList(String V_GUID);

    @WebMethod
    public String SetMonthPlanList(String V_GUID);


    @WebMethod
    String LoginInforXMLData(LoginInforData loginInforData);

}
