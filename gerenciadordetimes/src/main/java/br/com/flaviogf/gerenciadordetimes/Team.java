package br.com.flaviogf.gerenciadordetimes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Team {
    private final Long id;
    private final String name;
    private final LocalDate creationDate;
    private final String mainColor;
    private final String secondaryColor;
    private final Map<Long, Player> players = new HashMap<>();

    public Team(Long id, String name, LocalDate creationDate, String mainColor, String secondaryColor) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.mainColor = mainColor;
        this.secondaryColor = secondaryColor;
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

    public String getMainColor() {
        return mainColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public Optional<Player> getPlayer(Long id) {
        if (!players.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(players.get(id));
    }

    public int getCountPlayers() {
        return players.size();
    }

    public void add(Player player) {
        player.join(this);
        players.put(player.getId(), player);
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
