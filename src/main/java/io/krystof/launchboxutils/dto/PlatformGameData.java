package io.krystof.launchboxutils.dto;

import java.util.ArrayList;
import java.util.List;

import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class PlatformGameData {

	private String platformName;

	List<Game> games = new ArrayList<>();

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

}
