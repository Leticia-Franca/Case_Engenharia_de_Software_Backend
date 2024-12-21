package br.com.leticiafrag.case_engenharia_backend.domain;

import java.util.Random;
import java.util.UUID;

/*
* Represents a User in the system, storing essential user information such as
* name, email, age, and a unique identifier (id).
*
* The 'id' is generated using a UUID (Universally Unique Identifier), which
* is preferred over using a random long value (Random().nextLong()) because
* UUIDs have a much lower chance of generating duplicate values, ensuring
* better uniqueness.
*
* Constructor:
* - Initializes a new user with a name, email, and age, and automatically
* generates a unique 'id'.
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
