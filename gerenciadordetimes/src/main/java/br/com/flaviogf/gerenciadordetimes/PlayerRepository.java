package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public interface PlayerRepository {
    Result add(Player player);

    Optional<Player> findOne(Long id);
}
