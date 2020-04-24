package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public class GetPlayerName {
    private final PlayerRepository playerRepository;

    public GetPlayerName(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<String> execute(Long playerId) {
        Optional<Player> optionalPlayer = playerRepository.findOne(playerId);

        if (!optionalPlayer.isPresent()) {
            return Optional.empty();
        }

        Player player = optionalPlayer.get();

        return Optional.of(player.getName());
    }
}
