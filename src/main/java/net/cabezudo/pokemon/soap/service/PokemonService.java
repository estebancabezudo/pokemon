package net.cabezudo.pokemon.soap.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.cabezudo.pokemon.dto.Abilities;
import net.cabezudo.pokemon.dto.HeldItems;
import net.cabezudo.pokemon.dto.Pokemon;

@Service
public class PokemonService {

	private static final String POKEAPI_URI = "https://pokeapi.co/api/v2/pokemon/";

	private final RestTemplate restTemplate;

	public PokemonService(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	private Pokemon get(String name) {
		// TODO Add a cache for Pokemon object in order to call only one time to the
		// REST service per pokemon
		ResponseEntity<Pokemon> response = restTemplate.getForEntity(POKEAPI_URI + name, Pokemon.class);
		return response.getBody();
	}

	public Abilities getAbilities(String name) {
		Pokemon pokemon = get(name);
		return pokemon.getAbilities();
	}

	public int getBaseExperience(String name) {
		Pokemon pokemon = get(name);
		return pokemon.getBaseExperience();
	}

	public HeldItems getHeldItems(String name) {
		Pokemon pokemon = get(name);
		return pokemon.getHeldItems();
	}

	public int getId(String name) {
		Pokemon pokemon = get(name);
		return pokemon.getId();
	}

	public String getName(String name) {
		Pokemon pokemon = get(name);
		return pokemon.getName();
	}

	public String getLocationAreaEncounter(String name) {
		Pokemon pokemon = get(name);
		return pokemon.getLocationAreaEncounters();
	}
}
