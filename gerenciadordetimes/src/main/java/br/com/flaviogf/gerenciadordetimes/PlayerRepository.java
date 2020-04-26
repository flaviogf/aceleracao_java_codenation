package br.com.flaviogf.gerenciadordetimes;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    Result<Void> add(Player player);

    Optional<Player> findOne(Long id);

    List<Player> findAll();
}
