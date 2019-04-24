package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.DjqxEnity;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface DjService {

    //点检缺陷信息上传
    @WebMethod
    public void DJQX(DjqxEnity djqxExity);

}
