package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface DjService {

    //点检缺陷信息上传
    @WebMethod
    public List<DjqxReturnEnity> DJQX(DjqxEnity djqxExity);

    //检修工单创建结果下传
    @WebMethod
    public void SpotCheckProjRet(SpotCheckProjEnity spotCheckProjEnity);

    @WebMethod
    public String PM0002(@WebParam(name = "ORDER_NO") String ORDER_NO,
                         @WebParam(name = "STATUS") String STATUS,
                         @WebParam(name = "BUSINESS_TYPE") String BUSINESS_TYPE);

    @WebMethod
    public void StockIssue(@WebParam(name = "MATDOC_NOSAP") String MATDOC_NOSAP,
                           @WebParam(name = "MJAHR") String MJAHR,
                           @WebParam(name = "DOC_DATE") String DOC_DATE,
                           @WebParam(name = "PSTNG_DATE") String PSTNG_DATE,
                           @WebParam(name = "HEARDER_TXT") String HEARDER_TXT,
                           @WebParam(name = "SYSNAME") String SYSNAME,
                           List<linesOfItems> list);


}
