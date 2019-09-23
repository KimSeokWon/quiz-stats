package com.kim_seokwon.quiz.bank.statdevice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "USER") @Getter @Setter
public class User extends DateAudit{
    @Id
    private String username;
    private String password;

    private Set<Role> roles = new HashSet<>();

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
