package br.com.flaviogf.gerenciadordetimes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Player {
    private final Long id;
    private final Team team;
    private final String name;
    private final LocalDate birthday;
    private final Integer overall;
    private final BigDecimal balance;

    public Player(Long id, Team team, String name, LocalDate birthday, Integer overall, BigDecimal balance) {
        if (id == null) {
            throw new IllegalArgumentException("id must be not null.");
        }

        if (team == null) {
            throw new IllegalArgumentException("team must be not null.");
        }

        if (name == null) {
            throw new IllegalArgumentException("name must be not null.");
        }

        if (birthday == null) {
            throw new IllegalArgumentException("birthday must be not null.");
        }

        if (overall == null) {
            throw new IllegalArgumentException("overall must be not null.");
        }

        if (balance == null) {
            throw new IllegalArgumentException("balance must be not null.");
        }

        this.id = id;
        this.team = team;
        this.name = name;
        this.birthday = birthday;
        this.overall = overall;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Integer getOverall() {
        return overall;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
