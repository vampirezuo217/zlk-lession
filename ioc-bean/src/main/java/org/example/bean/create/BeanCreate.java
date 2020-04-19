package org.example.bean.create;

import org.example.bean.factory.DefaultUserFactory;
import org.example.bean.factory.UserFactory;
import org.example.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Bean实例化
 */
public class BeanCreate {
    public static void main(String[] args) {
        tradition();
        special();
    }

    private static void tradition() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-create-context.xml");
        traditionMethod(beanFactory);
    }

    private static void traditionMethod(BeanFactory beanFactory) {
        User bean1 = beanFactory.getBean("user-by-static-method", User.class);
        User bean2 = beanFactory.getBean("user-by-instance-method", User.class);
        User bean3 = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(bean1);
        System.out.println(bean2);
        System.out.println(bean3);
        System.out.println(bean1 == bean2);
        System.out.println(bean1 == bean3);
    }

    private static void special() {
        BeanFactory beanFactory1 = new ClassPathXmlApplicationContext("META-INF/special-bean-create-context.xml");
        specialMethod(beanFactory1);
    }

    private static void specialMethod(BeanFactory beanFactory) {
        // 文件配置中不区分重复bean，但是实际就会出现一个bean（去重）
        spiDemo();
        // ServiceLoaderFactoryBean方式
        serviceLoaderFactoryBeanMethod(beanFactory);
        // AutowireCapableBeanFactory方式
        autowireCapableBeanFactory();

    }

    /**
     * jdk的IOC
     */
    private static void spiDemo() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class,Thread.currentThread().getContextClassLoader());
        iteratorMethod(serviceLoader);
    }

    private static void serviceLoaderFactoryBeanMethod(BeanFactory beanFactory) {
        // ServiceLoaderFactoryBean里面去创建 ServiceLoader 关注类型是UserFactory
        ServiceLoader<UserFactory> userFactoryServiceLoader= beanFactory.getBean("userFactoryServiceLoader",ServiceLoader.class);
        iteratorMethod(userFactoryServiceLoader);
    }

    private static void autowireCapableBeanFactory() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/special-bean-create-context.xml");
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        // 实例化时  一定要写具体类 不是能是接口UserFactory
        UserFactory bean = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(bean.createUser());
    }

    private static void iteratorMethod(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
    }
}
