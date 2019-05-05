package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.DjqxEnity;
import org.building.pmservice.service.Enity.DjqxReturnEnity;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface DjService {

    //点检缺陷信息上传
    @WebMethod
    public List<DjqxReturnEnity> DJQX(DjqxEnity djqxExity);


}
