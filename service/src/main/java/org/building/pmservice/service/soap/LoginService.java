package org.building.pmservice.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface LoginService {
    @WebMethod
    String LoginInforXMLData(String GetXml);
}
