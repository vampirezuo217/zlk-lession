package org.example.dependency.injecttionn;

import org.example.annotaion.Super;
import org.example.domain.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 依赖注入
 */
public class DependencyInject {

    public static void main(String[] args) {
        // 配置xml文件
        // 启动spring应用上下文
//        BeanFactory beanFactory1 = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-inject-context.xml");
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-inject-context.xml");
        beanFactory.getParentBeanFactory();
        // 依赖来源（三种）
        userRespository(beanFactory);

    }

    private static void userRespository(ApplicationContext beanFactory) {
        //自定义bean
        UserRepository userRepository = customerBean(beanFactory);
        //内建依赖
        innerDependency(beanFactory, userRepository);
        //内建bean对象
        getInnerBean(beanFactory);
    }


    private static void getInnerBean(ApplicationContext beanFactory) {
        // 配置文件中没有定义 内部容器默认初始化了一些Bean
        // StandardEnvironment
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }

    private static void innerDependency(ApplicationContext beanFactory, UserRepository userRepository) {
        // 内建非bean对象(特殊bean)   依赖注入  通过ObjectFactoryCreatingFactoryBean方式（小插叙）
        // DefaultListableBeanFactory
        System.out.println(userRepository.getBeanFactory());
        // 他们不是同一个对象
        System.out.println(userRepository.getBeanFactory() == beanFactory);


        // NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available
        // 报错了 说明BeanFactory这个不是bean  依赖查找方式得到的对象
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        // 依赖查找和依赖注入 都是依赖（它可能来自于不同地方）-依赖源
    }

    private static UserRepository customerBean(ApplicationContext beanFactory) {
        // 依赖注入userRepository是bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");

        // 依赖注入bean对象
        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        return userRepository;
    }

}
