package org.example.dependency.lookup;

import org.example.annotaion.Super;
import org.example.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 */
public class DependencyLookup {

    public static void main(String[] args) {
        // 配置xml文件
        // 启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        // 按照名称（id）
        lookupRealTime(beanFactory);
        lookupLazyTime(beanFactory);

        // 按照单一类型
        lookupType(beanFactory);
        // 按照复合类型
        lookupCollect(beanFactory);

        //注解
        lookupAnnotionType(beanFactory);
        

    }

    private static void lookupAnnotionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap =  (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查询添加注解@Super的所有User集合对象   " + userMap);
        }
    }

    private static void lookupCollect(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("实时类型查询所有User集合对象   " + userMap);
        }
    }
    private static void lookupType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时类型查询   " + user);
    }


    private static void lookupLazyTime(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectfactory");
        User user = objectFactory.getObject();
        System.out.println("延迟名称查询   " + user);
    }

    private static void lookupRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时名称查找   " + user);
    }
}
