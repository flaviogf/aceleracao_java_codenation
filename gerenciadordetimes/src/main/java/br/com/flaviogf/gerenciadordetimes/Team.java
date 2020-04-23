package br.com.flaviogf.gerenciadordetimes;

import java.time.LocalDate;
import java.util.Objects;

public class Team {
    private final Long id;
    private final String name;
    private final LocalDate creationDate;
    private final String mainUniformColor;
    private final String secondaryUniformColor;

    public Team(Long id, String name, LocalDate creationDate, String mainUniformColor, String secondaryUniformColor) {
        if (id == null) {
            throw new IllegalArgumentException("id must be not null");
        }

        if (name == null) {
            throw new IllegalArgumentException("name must be not null");
        }

        if (creationDate == null) {
            throw new IllegalArgumentException("creationDate must be not null");
        }

        if (mainUniformColor == null) {
            throw new IllegalArgumentException("mainUniformColor must be not null");
        }

        if (secondaryUniformColor == null) {
            throw new IllegalArgumentException("secondaryUniform must be not null");
        }

        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.mainUniformColor = mainUniformColor;
        this.secondaryUniformColor = secondaryUniformColor;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getMainUniformColor() {
        return mainUniformColor;
    }

    public String getSecondaryUniformColor() {
        return secondaryUniformColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id.equals(team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
