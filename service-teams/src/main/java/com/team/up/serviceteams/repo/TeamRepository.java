package com.team.up.serviceteams.repo;

import com.team.up.serviceteams.repo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    Optional<Team> findById(Long Id);
}
