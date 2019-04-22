package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.WwqxbhEnity;
import org.building.pmservice.service.Enity.WxqxcljgEnity;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface WxjhService {
    @WebMethod
    public void WWQXCLJG(List<WxqxcljgEnity> items);

    @WebMethod
    public void WWQXBH(List<WwqxbhEnity> items);
}
