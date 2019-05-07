package ru.zagorodnikova.tm.spring;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;
import ru.zagorodnikova.tm.endpoint.TaskEndpoint;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
//@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class ServiceConfiguration {
//    @Bean
//    public SpringBus springBus() {
//        return new SpringBus();
//    }

    @Bean
    public Endpoint projectEndpoint() {
        Endpoint endpoint = new EndpointImpl( new ProjectEndpoint());
        endpoint.publish("/projectEndpoint");
        return endpoint;
    }

    @Bean
    public Endpoint taskEndpoint() {
        Endpoint endpoint = new EndpointImpl( new TaskEndpoint());
        endpoint.publish("/taskEndpoint");
        return endpoint;
    }
    @Bean
    public Endpoint userEndpoint() {
        Endpoint endpoint = new EndpointImpl( new UserEndpoint());
        endpoint.publish("/userEndpoint");
        return endpoint;
    }

}
