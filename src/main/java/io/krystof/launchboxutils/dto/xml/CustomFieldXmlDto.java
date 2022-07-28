package io.krystof.launchboxutils.dto.xml;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

@AutoProperty
public class CustomFieldXmlDto {

	@JsonProperty("GameID")
	private String gameId;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Value")
	private String value;

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

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
