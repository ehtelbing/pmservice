package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.DjqxEnity;
import org.building.pmservice.service.Enity.DjqxReturnEnity;
import org.building.pmservice.service.Enity.SpotCheckProjEnity;

import javax.jws.WebMethod;
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

}
