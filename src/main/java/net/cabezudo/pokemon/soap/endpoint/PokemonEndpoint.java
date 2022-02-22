package net.cabezudo.pokemon.soap.endpoint;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import net.cabezudo.pokemon.AbilitiesRequest;
import net.cabezudo.pokemon.AbilitiesResponse;
import net.cabezudo.pokemon.AbilityData;
import net.cabezudo.pokemon.BaseExperienceRequest;
import net.cabezudo.pokemon.BaseExperienceResponse;
import net.cabezudo.pokemon.HeldItem;
import net.cabezudo.pokemon.HeldItemsRequest;
import net.cabezudo.pokemon.HeldItemsResponse;
import net.cabezudo.pokemon.IdRequest;
import net.cabezudo.pokemon.IdResponse;
import net.cabezudo.pokemon.LocationAreaEncountersRequest;
import net.cabezudo.pokemon.LocationAreaEncountersResponse;
import net.cabezudo.pokemon.NameRequest;
import net.cabezudo.pokemon.NameResponse;
import net.cabezudo.pokemon.dto.Abilities;
import net.cabezudo.pokemon.dto.HeldItems;
import net.cabezudo.pokemon.soap.service.DataLogService;
import net.cabezudo.pokemon.soap.service.PokemonService;

@Endpoint
public class PokemonEndpoint {
	private static final String NAMESPACE_URI = "http://cabezudo.net/pokemon";
	private static final QName ABILITIES_QNAME = new QName("abilities");
	private static final QName BASE_EXPERIENCE_QNAME = new QName("base_experience");
	private static final QName HELD_ITEMS_QNAME = new QName("held_items");
	private static final QName ID_QNAME = new QName("id");
	private static final QName NAME_QNAME = new QName("name");
	private static final QName LOCATION_AREA_ENCOUNTERS_QNAME = new QName("location_area_encounters");

	@Autowired
	PokemonService pokemonService;

	@Autowired
	DataLogService dataLogService;

	private HttpServletRequest request;

	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Autowired
	public PokemonEndpoint() {
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "abilitiesRequest")
	@ResponsePayload
	public JAXBElement<AbilitiesResponse> Abilities(@RequestPayload AbilitiesRequest abilitiesRequest) {
		AbilitiesResponse response = new AbilitiesResponse();
		Abilities abilities = pokemonService.getAbilities(abilitiesRequest.getName());
		List<AbilityData> list = response.getAbilities();
		list.addAll(abilities);
		dataLogService.log(request, ABILITIES_QNAME.getLocalPart());
		return new JAXBElement<AbilitiesResponse>(ABILITIES_QNAME, AbilitiesResponse.class, response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "base_experienceRequest")
	@ResponsePayload
	public JAXBElement<BaseExperienceResponse> BaseExperience(
			@RequestPayload BaseExperienceRequest baseExperienceRequest) {
		BaseExperienceResponse response = new BaseExperienceResponse();
		int experience = pokemonService.getBaseExperience(baseExperienceRequest.getName());
		response.setBaseExperience(BigInteger.valueOf(experience));
		dataLogService.log(request, BASE_EXPERIENCE_QNAME.getLocalPart());
		return new JAXBElement<BaseExperienceResponse>(BASE_EXPERIENCE_QNAME, BaseExperienceResponse.class, response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "held_itemsRequest")
	@ResponsePayload
	public JAXBElement<HeldItemsResponse> HeldItems(@RequestPayload HeldItemsRequest heldItemsRequest) {
		HeldItemsResponse response = new HeldItemsResponse();
		HeldItems heldItems = pokemonService.getHeldItems(heldItemsRequest.getName());
		List<HeldItem> list = response.getHeldItems();
		list.addAll(heldItems);
		dataLogService.log(request, HELD_ITEMS_QNAME.getLocalPart());
		return new JAXBElement<HeldItemsResponse>(HELD_ITEMS_QNAME, HeldItemsResponse.class, response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "idRequest")
	@ResponsePayload
	public JAXBElement<IdResponse> Id(@RequestPayload IdRequest idRequest) {
		IdResponse response = new IdResponse();
		int id = pokemonService.getId(idRequest.getName());
		response.setId(BigInteger.valueOf(id));
		dataLogService.log(request, ID_QNAME.getLocalPart());
		return new JAXBElement<IdResponse>(ID_QNAME, IdResponse.class, response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "nameRequest")
	@ResponsePayload
	public JAXBElement<NameResponse> Name(@RequestPayload NameRequest nameRequest) {
		NameResponse response = new NameResponse();
		String name = pokemonService.getName(nameRequest.getName());
		response.setName(name);
		dataLogService.log(request, NAME_QNAME.getLocalPart());
		return new JAXBElement<NameResponse>(NAME_QNAME, NameResponse.class, response);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "location_area_encountersRequest")
	@ResponsePayload
	public JAXBElement<LocationAreaEncountersResponse> LocationAreaEncounters(
			@RequestPayload LocationAreaEncountersRequest locationAreaEncountersRequest) {
		LocationAreaEncountersResponse response = new LocationAreaEncountersResponse();
		String locationAreaEncounter = pokemonService.getLocationAreaEncounter(locationAreaEncountersRequest.getName());
		response.setLocationAreaEncounters(locationAreaEncounter);
		dataLogService.log(request, LOCATION_AREA_ENCOUNTERS_QNAME.getLocalPart());
		return new JAXBElement<LocationAreaEncountersResponse>(LOCATION_AREA_ENCOUNTERS_QNAME,
				LocationAreaEncountersResponse.class, response);
	}

}
