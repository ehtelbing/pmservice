package org.building.pmservice.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface LoginService {
    String LoginInforXMLData(@WebParam(name="clientXml") String GetXml);
}
