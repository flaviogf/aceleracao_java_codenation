package br.com.flaviogf.gerenciadordetimes;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class GetPlayersOfTheTeam {
    private final TeamRepository teamRepository;

    public GetPlayersOfTheTeam(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Result<List<Long>> execute(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findOne(teamId);

        if (!optionalTeam.isPresent()) {
            return Result.fail("Team was not found.");
        }

        Team team = optionalTeam.get();

        List<Long> playersId = team.getPlayers()
                .stream()
                .map(Player::getId)
                .collect(toList());

        return Result.ok(playersId);
    }
}
