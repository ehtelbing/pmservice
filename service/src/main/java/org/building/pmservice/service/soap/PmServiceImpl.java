package org.building.pmservice.service.soap;

import org.building.pmservice.service.repository.PmRepository;
import org.building.pmservice.service.soap.PmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.List;
import java.util.Map;

@WebService
@Component
public class PmServiceImpl implements PmService {

    @Autowired
    private PmRepository pmRepository;

    @Override
    public String getYearPlanList(String V_GUID) {
        List<Map<String, Object>> list = pmRepository.getYearPlanList(V_GUID);
        return "success";
    }

    @Override
    public String SetMonthPlanList(String V_GUID){
        List<Map<String, Object>> list = pmRepository.SetMonthPlanList(V_GUID);
        return "success";
    }
}
