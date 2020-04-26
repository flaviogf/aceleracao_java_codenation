package br.com.flaviogf.gerenciadordetimes;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GetTopPlayers {
    private final PlayerRepository playerRepository;

    public GetTopPlayers(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Long> execute(Integer limit) {
        Comparator<Player> comparisonCriteria = Comparator
                .comparingLong(Player::getOverall)
                .reversed()
                .thenComparingLong(Player::getId);

        return playerRepository
                .findAll()
                .stream()
                .sorted(comparisonCriteria)
                .map(Player::getId)
                .limit(limit)
                .collect(toList());
    }
}
