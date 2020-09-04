package com.deway.blog;

import com.deway.blog.config.GlobalConfig;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletContainerInitializer;

/**
 * 实现ServletContainerInitializer接口的类会被servlet容器发现并实例化调用onStartup方法
 *
 * @todo 区分这个两个getter方法的区别
 *
 * @see SpringServletContainerInitializer
 * @see ServletContainerInitializer
 * @author Deway
 */
public class WebBoot extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {GlobalConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

//    /**
//     *  do nothing
//     * @param servletContext  servletContext
//     * @throws ServletException  ServletException
//     */
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//        var encodingFilter = new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true, true);
//        servletContext
//            .addFilter("encodingFilter", encodingFilter)
//            .addMappingForUrlPatterns(EnumSet.of(
//                    DispatcherType.ASYNC,
//                    DispatcherType.ERROR,
//                    DispatcherType.FORWARD,
//                    DispatcherType.INCLUDE,
//                    DispatcherType.REQUEST),
//                    true, "/*");
//    }
}
