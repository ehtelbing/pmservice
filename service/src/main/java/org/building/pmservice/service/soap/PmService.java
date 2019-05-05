package org.building.pmservice.service.soap;


import org.building.pmservice.service.Enity.DbslEnity;
import org.building.pmservice.service.Enity.DbslReturnEnity;

import javax.jws.WebMethod;
import javax.jws.WebService;

//设备系统接口
@WebService
public interface PmService {
    @WebMethod
    public String getYearPlanList(String V_GUID);

    @WebMethod
    public String SetMonthPlanList(String V_GUID);


    @WebMethod
    public DbslReturnEnity DBSL(DbslEnity dbslEnity);

}
