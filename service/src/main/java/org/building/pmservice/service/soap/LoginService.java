package org.building.pmservice.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlType;

@WebService(targetNamespace = "http://actions/", endpointInterface = "org.building.pmservice.service.soap.LoginService")
public interface LoginService {
    //    (operationName = "http://actions", action = "LoginInforXMLData")
    @WebMethod(action = "http://actions/LoginInforXMLData")
//    @WebResult(name = "cilentXml", targetNamespace = "http://actions/")
    String LoginInforXMLData(@WebParam(targetNamespace = "http://actions/", name = "clientXml") String clientXml);
//    String LoginInforXMLData(@WebParam(name="clientXml") String GetXml);
}
