package org.example.bean.gc;

import org.example.bean.factory.UserFactory;
import org.example.bean.initialization.BeanInitializationAndDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGC {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationAndDestroy.class);
        applicationContext.refresh();

        // 按类型依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        applicationContext.close();
        System.out.println("Application 上下文已关闭。。。。。");
        Thread.sleep(2000);
        System.gc();
        Thread.sleep(1000);
    }
}
