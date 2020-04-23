package br.com.flaviogf.gerenciadordetimes;

import java.util.Optional;

public interface TeamRepository {
    Result add(Team team);

    Optional<Team> findOne(Long id);
}
