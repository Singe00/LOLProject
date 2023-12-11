package com.example.lwp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserDomain {
    @Id
    @Column(name = "email", columnDefinition = "VARCHAR(100)",nullable = false)
    private String email;

    @Column(name = "oauth_type", columnDefinition = "VARCHAR(50)")
    private String oauthType;
}
