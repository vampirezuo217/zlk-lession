package org.example.domain;

public class User {

    private Long id;
    private String name;

    /**
     * 静态工长方法提供一个bean
     */
    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("static factory create bean ");
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
