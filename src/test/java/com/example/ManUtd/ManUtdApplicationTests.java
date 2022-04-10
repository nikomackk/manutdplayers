package com.example.ManUtd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ManUtd.web.PlayerController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ManUtdApplicationTests {
	
	@Autowired
	private PlayerController playerController;
	
	@Test
	public void contextLoads() {
		assertThat(playerController).isNotNull();
	}

}
