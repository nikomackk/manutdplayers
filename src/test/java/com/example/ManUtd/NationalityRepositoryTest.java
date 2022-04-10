package com.example.ManUtd;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ManUtd.domain.Nationality;
import com.example.ManUtd.domain.NationalityRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class NationalityRepositoryTest {
	
	@Autowired
	private NationalityRepository nationalityRepository;

	@Test // testataan findByName() metodia
	public void findByNameShouldReturnId() {
		List<Nationality> nationalities = nationalityRepository.findByName("England");
		assertThat(nationalities).hasSize(1);
		assertThat(nationalities.get(0).getNationalityid()).isEqualTo(1);
	}
	
	@Test // Testataan save() metodia
	public void createNewNationality() {
		Nationality nationality = new Nationality("Testinationality");
		nationalityRepository.save(nationality);
		assertThat(nationality.getNationalityid()).isNotNull();
	}
	
	@Test // testataan delete() metodia
	public void deleteNationality() {
		Nationality nationality = new Nationality("Testinationality");
		nationalityRepository.save(nationality);
		nationalityRepository.deleteById(nationality.getNationalityid());
		assertThat(nationality.getName().isEmpty());
	}
}
