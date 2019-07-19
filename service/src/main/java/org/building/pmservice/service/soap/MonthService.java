package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface MonthService {
    @WebMethod
    public MonthxcReturnEnity Monxc(MonthxcEnity items);

    @WebMethod
    public WeekxcReturnEnity Weekxc(WeekxcEnity items);

    @WebMethod
    public WorkOrderRetEnity WorkOrder(WorkOrderInEnity items);

    @WebMethod
    public DefectRetEnity Defect(DefectEnity items);

    @WebMethod
    public EqucodeRetEnity DM0035(EqucodeEnity items);
}
