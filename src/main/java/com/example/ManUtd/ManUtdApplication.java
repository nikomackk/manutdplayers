package com.example.ManUtd;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ManUtd.domain.Nationality;
import com.example.ManUtd.domain.NationalityRepository;
import com.example.ManUtd.domain.Player;
import com.example.ManUtd.domain.PlayerRepository;
import com.example.ManUtd.domain.Position;
import com.example.ManUtd.domain.PositionRepository;
import com.example.ManUtd.domain.User;
import com.example.ManUtd.domain.UserRepository;



// 1. Spring Boot Web	OK
// 2. Thymeleaf			OK
// 3. JPA				OK
// 4. Security			OK
// 5. Validation		OK
// 6. REST				OK
// 7. JUnit Test		OK
// 8. REACT / Joku toiminnallisuus

@SpringBootApplication
public class ManUtdApplication {

	private static final Logger log = LoggerFactory.getLogger(ManUtdApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ManUtdApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner playerDemo(PlayerRepository playerRepository, NationalityRepository nationalityRepository, PositionRepository positionRepository, UserRepository userRepository ) {
		return (args) -> {
			
			log.info("Save nationalities"); // kansallisuudet databaseen - SQL INSERT
			Nationality eng = new Nationality("England");
			nationalityRepository.save(eng);
			Nationality por = new Nationality("Portugal");
			nationalityRepository.save(por);
			Nationality spa = new Nationality("Spain");
			nationalityRepository.save(spa);
			Nationality fra = new Nationality("France");
			nationalityRepository.save(fra);
			Nationality swe = new Nationality("Sweden");
			nationalityRepository.save(swe);
			
			
			log.info("Save positions"); // paikat databaseen - SQL INSERT
			Position def = new Position("Defender");
			positionRepository.save(def);
			Position att = new Position("Attacker");
			positionRepository.save(att);
			Position goa = new Position("Goalkeeper");
			positionRepository.save(goa);
			Position mid = new Position("Midfield");
			positionRepository.save(mid);
			
			
			log.info("Save players"); // pelaajia databaseen - SQL INSERT
			playerRepository.save(new Player("Cristiano Ronaldo", 7, por, att));
			playerRepository.save(new Player("Harry Maguire", 5, eng, def));
			playerRepository.save(new Player("David De Gea", 1, spa, goa));
			playerRepository.save(new Player("Victor Lindelöf", 2, swe, def));
			playerRepository.save(new Player("Raphael Varane", 19, fra, def));
			playerRepository.save(new Player("Paul Pogba", 6, fra, mid));
			playerRepository.save(new Player("Bruno Fernandes", 18, por, mid));
			playerRepository.save(new Player("Marcus Rashford", 10, eng, att));
			playerRepository.save(new Player("Jadon Sancho", 25, eng, att));
			
			// Tallennetaan Usereita databaseen SQL INSERT
			User user1 = new User("admin", "$2a$10$/4ldU1.mfBnGZ7vIZumMKeawgrLc6Ay2T1PQIcdA5.beuvPQy5mxi", "ADMIN" );
			userRepository.save(user1);
			User user2 = new User("user", "$2a$10$kzQajyf2P8EPIWPJZPT3keYpL9lSU.YnUFX0vTf2jARdPqVhwtTUe", "USER");
			userRepository.save(user2);
			
			log.info("Fetch all players"); // from database - SQL SELECT
			for (Player player : playerRepository.findAll()) {
				log.info(player.toString());
			}
			
			log.info("Fetch all nationalities"); // from database - SQL SELECT
			for (Nationality nationality : nationalityRepository.findAll()) {
				log.info(nationality.toString());
			}
			
			log.info("Fetch all positions"); // from database - SQL SELECT
			for (Position position : positionRepository.findAll()) {
				log.info(position.toString());
			}

		};
	}
}
