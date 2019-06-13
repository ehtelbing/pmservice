package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://soap.service.pmservice.building.org/", endpointInterface = "org.building.pmservice.service.soap.MonthService")
public interface MonthService {
    @WebMethod
    public MonthxcReturnEnity Monxc(MonthxcEnity items);

    @WebMethod
    public WeekxcReturnEnity Weekxc(WeekxcEnity items);

    @WebMethod
    public WorkOrderRetEnity WorkOrder(WorkOrderInEnity items);

    @WebMethod
    public DefectRetEnity Defect(DefectEnity items);
}
