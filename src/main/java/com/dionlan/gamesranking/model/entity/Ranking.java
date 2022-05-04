package com.dionlan.gamesranking.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Ranking {

	@Id
	private Long id;
}
