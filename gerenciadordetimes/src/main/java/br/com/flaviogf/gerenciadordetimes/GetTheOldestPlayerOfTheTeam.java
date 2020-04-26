package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class GetTheOldestPlayerOfTheTeam {
    private final TeamRepository teamRepository;

    public GetTheOldestPlayerOfTheTeam(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Result<Optional<Long>> execute(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findOne(teamId);

        if (!optionalTeam.isPresent()) {
            return Result.fail("Team was not found");
        }

        Team team = optionalTeam.get();

        if (!team.getTheOldestPlayer().isPresent()) {
            return Result.ok(Optional.empty());
        }

        Player player = team.getTheOldestPlayer().get();

        return Result.ok(Optional.of(player.getId()));
    }
}
