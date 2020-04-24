package br.com.flaviogf.gerenciadordetimes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class CreatePlayer {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public CreatePlayer(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public Result execute(Long id, Long teamId, String name, LocalDate birthday, Integer overall, BigDecimal balance) {
        Optional<Player> existingPlayer = playerRepository.findOne(id);

        if (existingPlayer.isPresent()) {
            return Result.fail("Id is already in use");
        }

        Optional<Team> optionalTeam = teamRepository.findOne(id);

        if (!optionalTeam.isPresent()) {
            return Result.fail("Team does not exist");
        }

        Player player = new Player(id, name, birthday, overall, balance);

        Team team = optionalTeam.get();

        team.add(player);

        return playerRepository.add(player);
    }
}
