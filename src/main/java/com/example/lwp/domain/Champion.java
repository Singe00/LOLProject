package com.example.lwp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Table(name = "champion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Champion {

    @Id
    @Column(name = "championid")
    private Long championId;

    @Column(name = "championname")
    private String championName;
}
