package com.dionlan.gamesranking.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionlan.gamesranking.model.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

}
