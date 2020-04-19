package org.example.bean.factory;

import org.example.domain.User;

/**
 * 抽象工厂类
 */
public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }

}
