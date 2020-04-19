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
 * BeanDefinition 注册
 * Java API 配置元信息
 */
public class ApiBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //顺序影响 BeanDefinition 注册 如果注释掉
        applicationContext.register(Config.class);

        // BeanDefinition注册Java API 配置元信息
        registerBeanDefinitionApi(applicationContext, "user");
        registerBeanDefinitionApi(applicationContext, "");

        //不会重复注册  同一个BeanFactory只有一个beanname标识
        applicationContext.register(Config.class);

        applicationContext.refresh();

        System.out.println(applicationContext.getBeansOfType(Config.class));
        System.out.println(applicationContext.getBeansOfType(User.class));
        applicationContext.close();
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


    @Component
    public static class Config {
        @Bean(name = {"user"}) //别名
        public User user() {
            User user = new User();
            user.setName("zlk222");
            user.setId(2L);
            return user;
        }
    }

}
