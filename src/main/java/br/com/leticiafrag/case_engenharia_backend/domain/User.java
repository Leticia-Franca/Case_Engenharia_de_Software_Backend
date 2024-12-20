package br.com.leticiafrag.case_engenharia_backend.domain;

import java.util.Random;

public class User {
    Long id;
    String name;
    String email;
    int age;

    public User(String name, String email, int age) {
        this.id = new Random().nextLong();
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
