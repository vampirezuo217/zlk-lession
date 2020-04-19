package org.example.bean.definition;

import org.example.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注解方式 beandefinition
 * config、component、user  都是bean  重复定义  不会重复注册
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注解方式
        annotationBeanDefinition(applicationContext);
        // 注册Java API 配置元信息
        callApiBeanDefinition(applicationContext);

        applicationContext.refresh();
        System.out.println(applicationContext.getBeansOfType(Config.class));
        System.out.println(applicationContext.getBeansOfType(User.class));
        applicationContext.close();
    }

    private static void annotationBeanDefinition(AnnotationConfigApplicationContext applicationContext) {
        // 注册spring bean
        // 1) 配置类 + Bean注解   + imports
//        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        // 2. 通过Component方式 + Bean
//        applicationContext.register(Config.class);

        // 3. @import导入
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
    }

    private static void callApiBeanDefinition(AnnotationConfigApplicationContext applicationContext) {
        // BeanDefinition注册Java API 配置元信息
        registerBeanDefinitionApi(applicationContext, "user");
        registerBeanDefinitionApi(applicationContext, "");

        //不会重复注册  同一个BeanFactory只有一个beanname标识
        applicationContext.register(ApiBeanDefinitionDemo.Config.class);
    }

    /**
     * 配置类Configuration class
     */
    @Component
    public static class Config {
        /**
         *  如何被spring上下文感知
         */
        @Bean(name = {"user"})//别名
        public User user() {
            User user = new User();
            user.setName("zlk222");
            user.setId(2L);
            return user;
        }
    }
    @Bean(name = {"user"})//别名
    public User user() {
        User user = new User();
        user.setName("zlk111");
        user.setId(1L);
        return user;
    }


    public static void registerBeanDefinitionApi(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "apiapiapiapiapi");
        if (StringUtils.hasText(beanName)) {
            // 命名bean方式
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名bean方式  org.example.domain.User#0=User  这种结构是方法registerWithGeneratedName构造出的
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }
}
