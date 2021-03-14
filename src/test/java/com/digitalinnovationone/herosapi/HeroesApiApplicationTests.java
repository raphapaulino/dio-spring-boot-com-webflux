package com.digitalinnovationone.herosapi;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import com.digitalinnovationone.herosapi.document.Heroes;
import com.digitalinnovationone.herosapi.repository.HeroesRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.print.attribute.standard.Media;
import java.util.List;

import static com.digitalinnovationone.herosapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;

	@Test
	public void getOneHeroById() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "3")
			.exchange()
			.expectStatus().isOk()
			.expectBody();
	}

	@Test
	public void getOneHeroNotFound() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	public void deleteHero() {
		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isNotFound()
			.expectBody(Void.class);
	}

}
