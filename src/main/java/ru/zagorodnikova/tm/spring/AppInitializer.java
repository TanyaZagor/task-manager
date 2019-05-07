package ru.zagorodnikova.tm.spring;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {JPAConfig.class, WebAppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ServiceConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/", "/services/*"};
    }


//    @Override
//    public void onStartup(ServletContext container) {
//
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(ServiceConfiguration.class);
//
//        container.addListener(new ContextLoaderListener(context));
//
//        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new CXFServlet());
//        dispatcher.setLoadOnStartup(1);
//
//        dispatcher.addMapping("/services/*");
//    }

    @Override
    protected void registerContextLoaderListener(ServletContext servletContext)
    {
        CXFServlet cxfServlet = new CXFServlet();
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("cxf", cxfServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/services/*");
    }


//    @Override
//    protected void registerDispatcherServlet(ServletContext servletContext) {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(ServiceConfiguration.class);
//
//        servletContext.addListener(new ContextLoaderListener(context));
//        CXFServlet cxfServlet = new CXFServlet();
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("cxf", cxfServlet);
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/services/*");
//    }
}
