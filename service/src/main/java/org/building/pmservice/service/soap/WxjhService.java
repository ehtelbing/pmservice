package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.WwqxbhEnity;
import org.building.pmservice.service.Enity.WwjsqxjgEnity;
import org.building.pmservice.service.Enity.WxqxcljgEnity;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface WxjhService {
    //委外缺陷处理结果上传
    @WebMethod
    public void WWQXCLJG(List<WxqxcljgEnity> items);

    //委外缺陷驳回上传
    @WebMethod
    public void WWQXBH(List<WwqxbhEnity> items);

    //委外竣工（决算）结果上传
    @WebMethod
    public void Wwjsqxjg(List<WwjsqxjgEnity> items);
}
