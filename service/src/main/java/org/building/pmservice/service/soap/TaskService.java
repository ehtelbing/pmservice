package org.building.pmservice.service.soap;

import org.building.pmservice.service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.w3c.dom.Document;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(cron = "0 */10 * * * ?")
    //@Scheduled(cron = "*/5 * * * * ?")
    @Async
    public void SetErrorAMToMess() {
        List result = taskRepository.SetWeekAMToMess("ERROR");
        if (result.size() > 0) {
            String ret = MessageGet(result);
            if (ret.equals("Success")) {
                UpdateAMToMessState("ERROR");
            }
        }

    }

    @Scheduled(cron = "0 0 13 ? * THU")
    @Async
    public void SetWeekAMToMess() {
        List result = taskRepository.SetWeekAMToMess("周计划");
        if (result.size() > 0) {
            String ret = MessageGet(result);
            if (ret.equals("Success")) {
                UpdateAMToMessState("周计划");
            }
        }
    }

    @Scheduled(cron = "0 0 9 22 * ?")
    @Async
    public void SetMonthAMToMess() {
        List result = taskRepository.SetWeekAMToMess("月计划");
        String ret = MessageGet(result);
        if (ret.equals("Success")) {
            UpdateAMToMessState("月计划");
        }
    }

    @Scheduled(cron = "0 0 9 1 11 ?")
    @Async
    public void SetYearAMToMess() {
        List result = taskRepository.SetWeekAMToMess("年计划");
        if (result.size() > 0) {
            String ret = MessageGet(result);
            if (ret.equals("Success")) {
                UpdateAMToMessState("年计划");
            }
        }
    }

    public void AMToMess(String xele, String Url) {

        try {
            String ns = "http://hoteamsoft.org/T";  // {1}
            String wsdlUrl = "http://10.101.10.13/NewsPlat/AMToMess.asmx?wsdl";  // {2}
            //String wsdlUrl = "http://192.168.3.105/NewsPlat/AMToMess.asmx?WSDL";  // {2}
            String username = "AKSB";
            String password = "AK_SB";
            //1、创建服务(Service)
            URL url = new URL(wsdlUrl);
            QName sname = new QName(ns, "AMToMess"); // {3}
            Service service = Service.create(url, sname);

            //2、创建Dispatch
            Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns, "AMToMessSoap12"), SOAPMessage.class, Service.Mode.MESSAGE); // {4}

            //3、创建SOAPMessage
            SOAPMessage msg = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
            SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
            SOAPBody body = envelope.getBody();

            //3.2、处理header信息
            SOAPHeader header = envelope.getHeader();
            if (header == null) header = envelope.addHeader();


            QName hname = new QName(ns, "AMToMessIFCheck", "t");
            SOAPHeaderElement sheader = header.addHeaderElement(hname);
            sheader.addChildElement("UserName").setValue(username);
            sheader.addChildElement("PassWord").setValue(password);


            //4、创建QName来指定消息中传递数据
            QName ename = new QName(ns, "AMToMessIFCheck", "t");//<nn:add xmlns="xx"/>  // {5}
            SOAPBodyElement ele = body.addBodyElement(ename);
            // 传递参数
            ele.addChildElement("xele").setValue(xele);
            ele.addChildElement("url").setValue(Url);
            msg.writeTo(System.out);

            //5、通过Dispatch传递消息,会返回响应消息
            SOAPMessage response = dispatch.invoke(msg);
            response.writeTo(System.out);

            //6、响应消息处理,将响应的消息转换为dom对象
            Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
            String str = doc.getElementsByTagName("AMToMessIFCheckResult").item(0).getTextContent();  // {7}

            //修改发送即时通状态

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String MessageGet(List result) {
        String ret = "Success";
        try {
            String strContent = "";
            String MSG = "";
            for (int i = 0; i < result.size(); i++) {
                Map map = (Map) result.get(i);
                String messtxt = "PM系统待办提醒";
                MSG = "<SendMessage><AM_Name>" + map.get("V_JST").toString()
                        + "</AM_Name><PhoneNum></PhoneNum><UserId></UserId><MessageTxt>"
                        + messtxt + "</MessageTxt><SystemName>设备管理系统</SystemName><Type>即时通</Type><Access></Access><Email></Email><IsBack></IsBack><IsEncrypt></IsEncrypt><ISPriority></ISPriority><Ohter1></Ohter1><Ohter2></Ohter2></SendMessage>";
                String loginurl = "http://10.101.25.134/pm/app/pm/page/login/login.html?v_mancode=" + map.get("V_LOGINNAME").toString() + "&v_type=newangel";

                strContent = "<HTML><BODY bgColor='#ffffff' style='font-family:Verdana,新宋体;font-size: 12px;'>";
                strContent += "<HR size='1' style='color: 52658C;'>";
                strContent += "待办任务提醒：<UL>";
                strContent += "<li>您有：" + map.get("NUM").toString() + " 条" + map.get("V_TYPE").toString() + "待办</li>";
                strContent += "</UL><a href='" + loginurl + "' target='_blank' >请点击这里进行办理</a></BODY></HTML>";
            }
            AMToMess(MSG, strContent);

        } catch (Exception e) {
            e.printStackTrace();
            ret = "Fail";
        }
        return ret;
    }

    public void UpdateAMToMessState(String type) {
        String result = taskRepository.UpdateAMToMessState(type);
    }

}
