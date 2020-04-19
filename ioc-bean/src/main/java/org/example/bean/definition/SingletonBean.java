package org.example.bean.definition;

import org.example.bean.factory.DefaultUserFactory;
import org.example.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 外部单体bean注册
 */
public class SingletonBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 这个对象和容器创建和初始化没有关系
        UserFactory userFactory = new DefaultUserFactory();

        //注册外部单例对象
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);
        applicationContext.refresh();

        // 依赖查找方式
        UserFactory bean =beanFactory.getBean("userFactory",UserFactory.class);
        System.out.println(userFactory);
        System.out.println(bean);
        System.out.println(userFactory == bean);
        applicationContext.close();

    }
}
