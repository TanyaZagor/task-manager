package ru.zagorodnikova.tm.spring;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint;
import ru.zagorodnikova.tm.api.endpoint.ITaskEndpoint;
import ru.zagorodnikova.tm.api.endpoint.IUserEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
@ComponentScan("ru.zagorodnikova.tm")
public class ServiceSoapConfiguration {

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Autowired
    private IUserEndpoint userEndpoint;

    @Autowired
    private IProjectEndpoint projectEndpoint;

    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Bean
    public Endpoint publishProjectEndpoint() {
        Endpoint endpoint = new EndpointImpl(springBus(), projectEndpoint);
        endpoint.publish("/projectEndpoint");
        return endpoint;
    }

    @Bean
    public Endpoint publishTaskEndpoint() {
        Endpoint endpoint = new EndpointImpl(springBus(),taskEndpoint);
        endpoint.publish("/taskEndpoint");
        return endpoint;
    }

    @Bean
    public Endpoint publishUserEndpoint() {
        Endpoint endpoint = new EndpointImpl(springBus(),userEndpoint);
        endpoint.publish("/userEndpoint");
        return endpoint;
    }

}
