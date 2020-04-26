package br.com.flaviogf.gerenciadordetimes;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
    Result<Void> add(Team team);

    Optional<Team> findOne(Long id);

    List<Team> findAll();
}
