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

    public Result<Void> execute(Long id, Long teamId, String name, LocalDate birthday, Integer overall, BigDecimal salary) {
        Optional<Player> existingPlayer = playerRepository.findOne(id);

        if (existingPlayer.isPresent()) {
            return Result.fail("Id is already in use");
        }

        Optional<Team> optionalTeam = teamRepository.findOne(teamId);

        if (!optionalTeam.isPresent()) {
            return Result.fail("Team was not found");
        }

        Player player = new Player(id, name, birthday, overall, salary);

        Team team = optionalTeam.get();

        team.add(player);

        return playerRepository.add(player);
    }
}

