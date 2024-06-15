package com.liemartt.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "matches", uniqueConstraints = {@UniqueConstraint(columnNames = {"player1", "player2"})})
@Getter
@Setter
@RequiredArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
