package br.com.flaviogf.gerenciadordetimes;

import java.time.LocalDate;
import java.util.*;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;

public class Team {
    private final Long id;
    private final String name;
    private final LocalDate creationDate;
    private final String mainColor;
    private final String secondaryColor;
    private final Map<Long, Player> players = new HashMap<>();
    private Player captain;
    private Player playerWithTheBiggestSalary;
    private Player theOldestPlayer;
    private Player theBestPlayer;

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

    public String getOutsideColor(Team other) {
        if (other.getMainColor().equals(mainColor)) {
            return getSecondaryColor();
        }

        return getMainColor();
    }

    public Optional<Player> getPlayer(Long id) {
        if (!players.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(players.get(id));
    }

    public Optional<Player> getCaptain() {
        if (captain == null) {
            return Optional.empty();
        }

        return Optional.of(captain);
    }

    public Optional<Player> getPlayerWithTheBiggestSalary() {
        if (playerWithTheBiggestSalary == null) {
            return Optional.empty();
        }

        return Optional.of(playerWithTheBiggestSalary);
    }

    public Optional<Player> getTheOldestPlayer() {
        if (theOldestPlayer == null) {
            return Optional.empty();
        }

        return Optional.of(theOldestPlayer);
    }

    public Optional<Player> getTheBestPlayer() {
        if (theBestPlayer == null) {
            return Optional.empty();
        }

        return Optional.of(theBestPlayer);
    }

    public Collection<Player> getPlayers() {
        return players.values()
                .stream()
                .sorted(comparingLong(Player::getId))
                .collect(toList());
    }

    public int getCountPlayers() {
        return players.size();
    }

    public void add(Player player) {
        players.put(player.getId(), player);
        setPlayerWithTheBiggestSalary(player);
        setTheOldestPlayer(player);
        setTheBestPlayer(player);
        player.join(this);
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    private void setPlayerWithTheBiggestSalary(Player player) {
        if (playerWithTheBiggestSalary == null || player.getSalary().compareTo(playerWithTheBiggestSalary.getSalary()) > 0) {
            playerWithTheBiggestSalary = player;
        }
    }

    private void setTheOldestPlayer(Player player) {
        if (theOldestPlayer == null || player.getBirthday().isBefore(theOldestPlayer.getBirthday())) {
            theOldestPlayer = player;
        }
    }

    private void setTheBestPlayer(Player player) {
        if (theBestPlayer == null || theBestPlayer.getOverall() < player.getOverall()) {
            theBestPlayer = player;
        }
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
