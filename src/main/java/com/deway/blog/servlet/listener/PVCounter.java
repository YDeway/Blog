package com.deway.blog.servlet.listener;

import com.deway.blog.controller.MainController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicLong;

/**
 * tomcat根据访问的IP地址对request和response内存地址进行维护,如果该内存长时间（默认30分钟）
 * 没有reqeust和response对象，将会销毁该内存地址，或是存放其他的对象
 * @author Deway
 */
@WebListener
public class PVCounter implements HttpSessionListener, ServletRequestListener {

    private final AtomicLong requestCount = new AtomicLong(0L);
    private final AtomicLong currentOnline = new AtomicLong(0L);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        currentOnline.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        currentOnline.decrementAndGet();
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
//        WebApplicationContext attribute = (WebApplicationContext)sre.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        requestCount.getAndIncrement();
    }

}
