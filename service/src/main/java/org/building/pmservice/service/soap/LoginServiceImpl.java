package org.building.pmservice.service.soap;

import org.building.pmservice.service.repository.LoginRepository;
import org.building.pmservice.service.repository.PmRepository;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public String LoginInforXMLData(String clientXml) {
        String result = null;
        try {
            Document doc = DocumentHelper.parseText(clientXml);

            Element rootElt = doc.getRootElement();
            String USERNAME = rootElt.element("USERNAME").getText();
            String PASSWORD = rootElt.element("PASSWORD").getText();
            String BEGINTIME = rootElt.element("BEGINTIME").getText();
            String ENDTIME = rootElt.element("ENDTIME").getText();

            result = loginRepository.LoginInforXMLData(USERNAME, PASSWORD, BEGINTIME, ENDTIME);

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return result;
    }
}
