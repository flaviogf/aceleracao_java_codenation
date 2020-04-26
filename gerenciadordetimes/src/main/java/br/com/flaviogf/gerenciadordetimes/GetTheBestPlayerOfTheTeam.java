package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class GetTheBestPlayerOfTheTeam {
    private final TeamRepository teamRepository;

    public GetTheBestPlayerOfTheTeam(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Result<Optional<Long>> execute(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findOne(teamId);

        if (!optionalTeam.isPresent()) {
            return Result.fail("Team was not found");
        }

        Team team = optionalTeam.get();

        if (!team.getTheBestPlayer().isPresent()) {
            return Result.ok(Optional.empty());
        }

        Player player = team.getTheBestPlayer().get();

        return Result.ok(Optional.of(player.getId()));
    }
}
