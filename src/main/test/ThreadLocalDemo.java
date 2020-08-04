import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadLocalDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);
    private static ThreadLocal<String> threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            int j = i + 1;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "---设值前threadLocal=" + threadLocal.get());
                threadLocal.set(Thread.currentThread().getName() + " and 第" + j + "个请求");
                System.out.println(Thread.currentThread().getName() + "---设值后threadLocal=" + threadLocal.get());
            });
        }
        executorService.shutdown();
    }
}

