package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class GetCaptain {
    private final TeamRepository teamRepository;

    public GetCaptain(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Result<Long> execute(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findOne(teamId);

        if (!optionalTeam.isPresent()) {
            return Result.fail("Time was not found.");
        }

        Team team = optionalTeam.get();

        Optional<Player> optionalCaptain = team.getCaptain();

        if (!optionalCaptain.isPresent()) {
            return Result.fail("Team does not have a captain.");
        }

        Player captain = optionalCaptain.get();

        return Result.ok(captain.getId());
    }
}
