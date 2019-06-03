package org.building.pmservice.service.soap;

import org.building.pmservice.service.Enity.*;
import org.building.pmservice.service.repository.DjRepository;
import org.building.pmservice.service.repository.WxjhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService
@Component
public class DjServiceImpl implements DjService {

    @Autowired
    private DjRepository djRepository;

    @Autowired
    private WxjhRepository wxjhRepository;


    @Override
    public List<DjqxReturnEnity> DJQX(DjqxEnity djqxEnity) {

        List<QxEnity> list = djqxEnity.getItems();
        List<DjqxReturnEnity> ret = new ArrayList<DjqxReturnEnity>();
        Map result = new HashMap();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                DjqxReturnEnity r = new DjqxReturnEnity();
                QxEnity qxEnity = list.get(i);
                try {
                    result = djRepository.ImportDefect(djqxEnity.getV_SYSTEM(), qxEnity.getI_ID(), qxEnity.getV_DEFECTLIST(), qxEnity.getV_DEFTYPE(), qxEnity.getV_SOURCEID(), qxEnity.getD_DEFECTDATE(), qxEnity.getD_INDATE(),
                            qxEnity.getV_PERCODE(), qxEnity.getV_PERNAME(), qxEnity.getV_DEPTCODE(), qxEnity.getV_EQUCODE(), qxEnity.getV_IDEA(), qxEnity.getV_EQUSITE(), qxEnity.getV_EQUCHILDCODE(),
                            qxEnity.getV_INPERCODE(), qxEnity.getV_INPERNAME(), qxEnity.getV_EQUTYPECODE(), qxEnity.getV_ORGCODE(), qxEnity.getV_BZ());
                    if (result.get("ret").equals("SUCCESS")) {
                        r.setV_GUID(qxEnity.getI_ID());
                        r.setV_INFO("缺陷信息上传成功");
                        r.setV_TYPE("S");
                        wxjhRepository.WebServiceLog(djqxEnity.getV_SYSTEM(), qxEnity.getI_ID(), "成功", "点检缺陷信息上传WebService成功，信息插入成功！唯一值为缺陷ID" + qxEnity.getI_ID() + qxEnity.getV_SOURCEID());
                    } else {
                        wxjhRepository.WebServiceLog(djqxEnity.getV_SYSTEM(), qxEnity.getI_ID(), "失败", "点检缺陷信息上传WebService成功，信息插入失败！唯一值为缺陷ID" + qxEnity.getI_ID() + qxEnity.getV_SOURCEID());
                        r.setV_GUID(qxEnity.getI_ID());
                        r.setV_INFO("缺陷信息上传成功，插入失败");
                        r.setV_TYPE("S");
                    }
                } catch (Exception e) {
                    wxjhRepository.WebServiceLog(djqxEnity.getV_SYSTEM(), qxEnity.getI_ID(), "失败", "点检缺陷信息上传WebService成功，信息插入失败！失败信息为" + e.getMessage() + "唯一值为缺陷ID" + qxEnity.getI_ID() + qxEnity.getV_SOURCEID());
                    r.setV_GUID(qxEnity.getI_ID());
                    r.setV_INFO("缺陷信息上传失败,失败信息为" + e.getMessage());
                    r.setV_TYPE("S");
                }
                ret.add(r);
            }
        }
        return ret;
    }

}
