package org.building.pmservice.service.soap;

import org.building.pmservice.service.repository.ProduceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@WebService
@Component
public class ProduceServiceImpl implements ProduceService {

    @Autowired
    private ProduceRepository produceRepository;

    @Override
    public String WeekPlanRunData(String V_V_CKCODE, String V_V_CKNAME, String V_V_ZYQCODE, String V_V_ZYQNAME, String V_V_EQUCODE,
                           String V_V_EQUNAME, String V_V_PLAN_TIME_B, String V_V_PLAN_TIME_E, String V_V_FACT_TIME_B, String V_V_FACT_TIME_E,
                           String V_V_REASON, String V_V_TYPE) {

        String result = produceRepository.PRO_PM_PLAN_SC_DATA_SET(V_V_CKCODE, V_V_CKNAME, V_V_ZYQCODE, V_V_ZYQNAME, V_V_EQUCODE,
                V_V_EQUNAME, V_V_PLAN_TIME_B, V_V_PLAN_TIME_E, V_V_FACT_TIME_B, V_V_FACT_TIME_E,
                V_V_REASON, V_V_TYPE);

        return result;
    }
}
