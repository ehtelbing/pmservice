package org.building.pmservice.service.soap;

import org.building.pmservice.service.repository.PmRepository;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.Iterator;
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
    public String SetMonthPlanList(String V_GUID) {
        List<Map<String, Object>> list = pmRepository.SetMonthPlanList(V_GUID);
        return "success";
    }

}
