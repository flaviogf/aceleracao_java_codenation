package br.com.flaviogf.gerenciadordetimes;

import java.math.BigDecimal;
import java.util.Optional;

public class GetPlayerSalary {
    private final PlayerRepository playerRepository;

    public GetPlayerSalary(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<BigDecimal> execute(Long playerId) {
        Optional<Player> optionalPlayer = playerRepository.findOne(playerId);

        return optionalPlayer.map(Player::getSalary);
    }
}
