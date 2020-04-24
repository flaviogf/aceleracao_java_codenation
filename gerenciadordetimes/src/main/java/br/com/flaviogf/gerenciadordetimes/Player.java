package br.com.flaviogf.gerenciadordetimes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class Player {
    private final Long id;
    private final String name;
    private final LocalDate birthday;
    private final Integer overall;
    private final BigDecimal balance;
    private Optional<Team> team = Optional.empty();

    public Player(Long id, String name, LocalDate birthday, Integer overall, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.overall = overall;
        this.balance = balance;
    }

    public Long getId() {
        return id;
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

    public Optional<Team> getTeam() {
        return team;
    }

    public void join(Team team) {
        this.team = Optional.of(team);
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
