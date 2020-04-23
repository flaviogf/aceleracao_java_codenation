package br.com.flaviogf.gerenciadordetimes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class CreatePlayer {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public CreatePlayer(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public Result execute(Long id, Long teamId, String name, LocalDate birthday, Integer overall, BigDecimal balance) {
        Optional<Player> existingPlayer = playerRepository.findOne(id);

        if (existingPlayer.isPresent()) {
            return Result.fail("This player already exists.");
        }

        Optional<Team> team = teamRepository.findOne(teamId);

        if (!team.isPresent()) {
            return Result.fail("This team not exists.");
        }

        Player player = new Player(id, team.get(), name, birthday, overall, balance);

        return playerRepository.add(player);
    }
}
