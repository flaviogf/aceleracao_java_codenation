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
    private final BigDecimal salary;
    private Team team;

    public Player(Long id, String name, LocalDate birthday, Integer overall, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.overall = overall;
        this.salary = salary;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public Optional<Team> getTeam() {
        if (team == null) {
            return Optional.empty();
        }

        return Optional.of(team);
    }

    public void join(Team team) {
        this.team = team;
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
