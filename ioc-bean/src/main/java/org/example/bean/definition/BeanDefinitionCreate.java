package org.example.bean.definition;

import org.example.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanDefinitionCreate {

    public static void main(String[] args) {

        // 定义Bean元信息
        builder();
        abstractmethod();

    }

    private static void abstractmethod() {
        // 通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("id",1).add("name","zlk");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }

    private static void builder() {
        // 通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性
        beanDefinitionBuilder.addPropertyValue("id",1).addPropertyValue("name","zlk");
        // 获取BeanDefinition实例  且并非最终状态  可以自定义修改
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    }
}
