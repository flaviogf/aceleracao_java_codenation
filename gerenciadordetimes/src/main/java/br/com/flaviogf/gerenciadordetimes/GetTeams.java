package br.com.flaviogf.gerenciadordetimes;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GetTeams {
    private final TeamRepository teamRepository;

    public GetTeams(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Long> execute() {
        return this.teamRepository
                .findAll()
                .stream()
                .map(Team::getId)
                .sorted()
                .collect(toList());
    }
}
