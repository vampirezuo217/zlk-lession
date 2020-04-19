package org.example.bean.factory;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认实现类
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    /**
     * 注解
     */
    @PostConstruct
    public void initByPostConstruct() {
        System.out.println("通过注解 PostConstruct 方式 初始化中。。。。。");
    }
    /**
     * 实现 InitializingBean接口的 afterPropertiesSet() 方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet 初始化方式 初始化中。。。。。");
    }

    /**
     * 自定义(Bean标签 添加属性)
     */
    public void initByCustomer() {
        System.out.println("自定义初始化方式 初始化中。。。。。");
    }


    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy注解方式 销毁中。。。。。");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean接口方式 销毁中。。。。。");
    }

    public void destoryByCustomer(){
        System.out.println("自定义方式 销毁中。。。。。");
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("当前对象正在被垃圾回收gc");
    }
}
