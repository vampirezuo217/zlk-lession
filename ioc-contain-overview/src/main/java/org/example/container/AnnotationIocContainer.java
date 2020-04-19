package org.example.container;

import org.example.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AnnotationIocContainer {
    public static void main(String[] args) {
        // 创建容器 applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 当前类作为配置类
        applicationContext.register(AnnotationIocContainer.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找
        lookupcollectByType(applicationContext);

        applicationContext.close();
    }
    // java注解方式定义一个Bean   Bean定义
    // java代码方式代替配置 xml同等的方式
    @Bean
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("zlk");
        return user;
    }
    private static void lookupcollectByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("实时类型查询所有User集合对象   " + userMap);
        }
    }
}
