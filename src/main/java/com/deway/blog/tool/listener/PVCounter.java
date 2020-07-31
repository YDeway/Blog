package com.deway.blog.tool.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicLong;


@WebListener
public class PVCounter implements HttpSessionListener, ServletRequestListener {

    private AtomicLong requestCount = new AtomicLong(0L);
    private AtomicLong currentOnline = new AtomicLong(0L);


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
        requestCount.getAndIncrement();
    }

}
