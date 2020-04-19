package org.example.repository;

import org.example.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;

import java.util.Collection;

/**
 * 用户信息仓库
 */
public class UserRepository {

    /**
     * 自定义bean
     */
    private Collection<User> users;

    /**
     * 内建非bean对象（依赖）
     */
    private BeanFactory beanFactory;

    private ObjectFactory<User> objectFactory;


    public ObjectFactory<User> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<User> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }


}
