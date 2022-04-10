package com.example.ManUtd;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ManUtd.domain.Position;
import com.example.ManUtd.domain.PositionRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PositionRepositoryTest {
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Test // Testataan findByName() metodia
	public void findByNameShouldReturnId() {
		List<Position> positions = positionRepository.findByName("Defender");
		
		assertThat(positions).hasSize(1);
		assertThat(positions.get(0).getPositionid()).isEqualTo(6);
	}
	
	@Test // Testataan save() metodia
	public void createNewPosition() {
		Position position = new Position("Testipositio");
		positionRepository.save(position);
		assertThat(position.getPositionid()).isNotNull();
	}
	
	@Test // Testataan delete() metodia
	public void deletePosition() {
		Position position = new Position("Testipositio");
		positionRepository.save(position);
		positionRepository.deleteById(position.getPositionid());
		assertThat(position.getName().isEmpty());
	}
}
