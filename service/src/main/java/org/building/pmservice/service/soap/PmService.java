package org.building.pmservice.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
//设备系统接口
@WebService
public interface PmService {
    @WebMethod
    public String getYearPlanList(String V_GUID);
}