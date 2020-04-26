package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class GetColorOfTheOutsideTeam {
    private final TeamRepository teamRepository;

    public GetColorOfTheOutsideTeam(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Result<String> execute(Long homeTeamId, Long outsideTeamId) {
        Optional<Team> optionalOutsideTeam = teamRepository.findOne(outsideTeamId);

        if (!optionalOutsideTeam.isPresent()) {
            return Result.fail("Outside was not found");
        }

        Optional<Team> optionalHomeTeam = teamRepository.findOne(homeTeamId);

        if (!optionalHomeTeam.isPresent()) {
            return Result.fail("Home was not found");
        }

        Team outsideTeam = optionalOutsideTeam.get();

        Team homeTeam = optionalHomeTeam.get();

        String outsideColor = outsideTeam.getOutsideColor(homeTeam);

        return Result.ok(outsideColor);
    }
}
