package com.kim_seokwon.quiz.bank.statdevice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Document(collection = "ROLE")
public class Role {
    @Id
    private Long id;
    private RoleName name;
}
