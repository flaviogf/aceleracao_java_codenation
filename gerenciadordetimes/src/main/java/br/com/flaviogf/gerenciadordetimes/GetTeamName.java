package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class GetTeamName {
    private final TeamRepository teamRepository;

    public GetTeamName(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Optional<String> execute(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findOne(teamId);

        if (!optionalTeam.isPresent()) {
            return Optional.empty();
        }

        Team team = optionalTeam.get();

        return Optional.of(team.getName());
    }
}
