package org.building.pmservice.confing;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.building.pmservice.service.soap.PmService;
import org.building.pmservice.service.soap.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/webService/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Autowired
    private ProduceService produceService;
    @Autowired
    private PmService pmService;

    @Autowired
    private Bus bus;
    //终端路径

    @Bean
    public Endpoint AppServicePoint() {
        EndpointImpl endpointApp = new EndpointImpl(bus, produceService);
        endpointApp.publish("/produceService");
        return endpointApp;
    }

    //终端路径
    @Bean
    public Endpoint PmServicePoint() {
        EndpointImpl endpointApp = new EndpointImpl(bus, pmService);
        endpointApp.publish("/pmService");
        return endpointApp;
    }

}