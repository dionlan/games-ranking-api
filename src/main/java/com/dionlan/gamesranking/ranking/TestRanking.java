package com.dionlan.gamesranking.ranking;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TestRanking {
	
	public static void main(String[] args) {
	
		List<Player> players = Player.bancoDePessoas();
		//Stream<Player> games = players.stream().sorted(Comparator.comparing(Game::getId).reversed());
		
		/*
		List<Player> listaStream = players
				.stream()
				.sorted(Comparator.comparing(t -> t.getGame().getTotalWins(), Comparator.reverseOrder()))
				.collect(Collectors.toList());
		
		Map<Object, Object> m = players
				.stream()
				.collect(Collectors.groupingBy(x -> x.getGame().getTotalWins())
				).entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())) 
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		*/
		
		AtomicInteger index = new AtomicInteger(1);
		Map<Integer, Player> nameMap =  players.stream()
		    .sorted(Comparator.comparing(t -> t.getGame().getTotalWins(), Comparator.reverseOrder()))
		    .collect(Collectors
		            .toMap(value -> index.getAndIncrement(), value -> value));

		List<Entry<Integer, Player>> list = List.copyOf(nameMap.entrySet());
		System.out.println(list);
	}
}
