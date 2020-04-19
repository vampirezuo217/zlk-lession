package org.example.bean.initialization;

import org.example.bean.factory.DefaultUserFactory;
import org.example.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInitializationAndDestroy {

    public static void main(String[] args) {
        // 注解方式
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationAndDestroy.class);
        applicationContext.refresh();
        System.out.println("Application 上下文启动。。。。。");

        // 按类型依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Application 上下文准备关闭。。。。。");
        applicationContext.close();
        System.out.println("Application 上下文已关闭。。。。。");

    }

    @Bean(initMethod = "initByCustomer",destroyMethod = "destoryByCustomer")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
