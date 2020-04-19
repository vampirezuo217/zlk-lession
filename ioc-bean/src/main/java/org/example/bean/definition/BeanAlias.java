package org.example.bean.definition;

import org.example.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 别名
 */
public class BeanAlias {

    public static void main(String[] args) {

        BeanFactory factory = new ClassPathXmlApplicationContext("META-INF/bean-definitions-context.xml");
        User bean = factory.getBean("xiaomage-user", User.class);
        User bean1 = factory.getBean("user", User.class);
        System.out.println(bean == bean1);

    }
}
