package io.krystof.launchboxutils.dto.xml;

import java.util.ArrayList;
import java.util.List;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@AutoProperty
public class PlatformFileXmlDto {

	@JsonProperty("Game")
	@JacksonXmlElementWrapper(useWrapping = false)
	List<GameXmlDto> games = new ArrayList<>();

	@JsonProperty("CustomField")
	@JacksonXmlElementWrapper(useWrapping = false)
	List<CustomFieldXmlDto> customFields = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		return Pojomatic.equals(this, o);
	}

	@Override
	public int hashCode() {
		return Pojomatic.hashCode(this);
	}

	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}

	public List<GameXmlDto> getGames() {
		return games;
	}

	public void setGames(List<GameXmlDto> games) {
		this.games = games;
	}

	public List<CustomFieldXmlDto> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomFieldXmlDto> customFields) {
		this.customFields = customFields;
	}
}
