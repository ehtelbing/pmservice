package org.building.pmservice.service.soap;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

//设备系统接口
@WebService(targetNamespace = "http://soap.service.pmservice.building.org/", endpointInterface = "org.building.pmservice.service.soap.PmService")
public interface PmService {

    //委外缺陷信息下传
    @WebMethod
    public String PM0011(@WebParam(targetNamespace = "http://soap.service.pmservice.building.org/", name = "clientXml") String clientXml);

    //检修完成结果下传
    @WebMethod
    public String PM0014(@WebParam(targetNamespace = "http://soap.service.pmservice.building.org/", name = "clientXml") String clientXml);

    //点检缺陷处理结果反馈
    @WebMethod
    public String PM0010(@WebParam(targetNamespace = "http://soap.service.pmservice.building.org/", name = "clientXml") String clientXml);

    //人员权限上传
    @WebMethod
    public String PR1003(@WebParam(targetNamespace="http://soap.service.pmservice.building.org/",name="clientXml") String clientXml);

    //Sap工单上传
    @WebMethod
    public String PM1001(@WebParam(targetNamespace="http://soap.service.pmservice.building.org/",name="clientXml") String clientXml);

}
