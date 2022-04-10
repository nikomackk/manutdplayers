package com.example.ManUtd;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ManUtd.domain.Player;
import com.example.ManUtd.domain.PlayerRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlayerRepositoryTest {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	// testataan PlayerRepositoryn findByName() metodia
	@Test
	public void findByNameShouldReturnId() {
		List<Player> players = playerRepository.findByName("Cristiano Ronaldo");
		
		assertThat(players).hasSize(1);
		assertThat(players.get(0).getId()).isEqualTo(10);
		
	}
	
	@Test // testataan PlayerRepositoryn save() metodia
	public void createNewPlayer() {
		Player player = new Player("Testi Pelaaja", 99, null, null);
		playerRepository.save(player);
		assertThat(player.getId()).isNotNull();
	}
	
	@Test // testataan PlayerRepositoryn delete() metodia
	public void deletePlayer() {
		Player player = new Player("Testi Pelaaja", 99, null, null);
		playerRepository.save(player);
		playerRepository.deleteById(player.getId());
		assertThat(player.getName().isEmpty());
	}
}
