package com.dionlan.gamesranking.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dionlan.gamesranking.model.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

	boolean existsByNickname(String nickname);
	
	Player findByNickname(String nickname);
}
