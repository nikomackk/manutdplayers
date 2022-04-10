package com.example.ManUtd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ManUtd.domain.User;
import com.example.ManUtd.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test // testataan findByName() metodia
	public void findByUserNameShouldReturnId() {
		User user = userRepository.findByUsername("admin");
		assertThat(user.getId()).isEqualTo(1);
	}
	
	@Test // testataan save metodia
	public void addNewUser() {
		User user = new User("testi", "salasana", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	/*@Test // testataan delete metodia | Onko useria edes mahdollista poistaa?
	public void deleteUser() {
		User user = new User("testi", "salasana", "USER");
		userRepository.save(user);
		userRepository.deleteById(user.getId());
		assertThat(user.getUsername()).isEmpty();
	}*/
}
