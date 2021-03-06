package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class SetCaptain {
    private final PlayerRepository playerRepository;

    public SetCaptain(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Result<Void> execute(Long playerId) {
        Optional<Player> optionalPlayer = playerRepository.findOne(playerId);

        if (!optionalPlayer.isPresent()) {
            return Result.fail("Player was not found");
        }

        Player player = optionalPlayer.get();

        player.getTeam().ifPresent(team -> team.setCaptain(player));

        return Result.ok();
    }
}
