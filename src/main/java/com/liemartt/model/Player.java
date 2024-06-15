package com.liemartt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "players", indexes = @Index(name = "nameIndex", columnList = "name"))
@Getter
@Setter
@RequiredArgsConstructor
public class Player {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    public Player(String name) {
        this.name = name;
    }
}
