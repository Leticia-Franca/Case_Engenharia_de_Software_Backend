package br.com.leticiafrag.case_engenharia_backend.domain;

import java.util.Random;
import java.util.UUID;

/*
*
* Using UUID to generate a value for 'String id' because, compared to Random().Long(),
* it has lesser risk of duplicating numbers
* */
public class User {
    String id;
    String name;
    String email;
    int age;

    public User(String name, String email, int age) {
        this.id = UUID.randomUUID().toString().replace("-", "");
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
