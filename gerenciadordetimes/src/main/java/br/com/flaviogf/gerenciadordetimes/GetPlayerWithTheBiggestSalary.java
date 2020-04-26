package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class GetPlayerWithTheBiggestSalary {
    private final TeamRepository teamRepository;

    public GetPlayerWithTheBiggestSalary(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Result<Optional<Long>> execute(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findOne(teamId);

        if (!optionalTeam.isPresent()) {
            return Result.fail("Team was not found");
        }

        Team team = optionalTeam.get();

        if (!team.getPlayerWithTheBiggestSalary().isPresent()) {
            return Result.ok(Optional.empty());
        }

        Player player = team.getPlayerWithTheBiggestSalary().get();

        return Result.ok(Optional.of(player.getId()));
    }
}
