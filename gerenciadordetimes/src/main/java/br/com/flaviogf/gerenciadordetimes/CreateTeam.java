package br.com.flaviogf.gerenciadordetimes;

import java.time.LocalDate;
import java.util.Optional;

public class CreateTeam {
    private final TeamRepository teamRepository;

    public CreateTeam(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Result execute(Long id, String name, LocalDate creationDate, String mainUniformColor, String secondaryUniformColor) {
        Optional<Team> existingTeam = teamRepository.findOne(id);

        if (existingTeam.isPresent()) {
            return Result.fail("This team already exists.");
        }

        Team team = new Team(id, name, creationDate, mainUniformColor, secondaryUniformColor);

        return teamRepository.add(team);
    }
}
