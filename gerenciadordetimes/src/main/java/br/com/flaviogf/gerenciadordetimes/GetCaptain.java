package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class GetCaptain {
    private final TeamRepository teamRepository;

    public GetCaptain(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Result<Optional<Long>> execute(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findOne(teamId);

        if (!optionalTeam.isPresent()) {
            return Result.fail("Time was not found.");
        }

        Team team = optionalTeam.get();

        Optional<Player> optionalCaptain = team.getCaptain();

        if (!optionalCaptain.isPresent()) {
            return Result.ok(Optional.empty());
        }

        Player captain = optionalCaptain.get();

        Long id = captain.getId();

        return Result.ok(Optional.of(id));
    }
}
