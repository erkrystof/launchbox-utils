package io.krystof.launchboxutils.model;

import java.util.HashMap;
import java.util.Map;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class LaunchBoxDataModel {

	private Map<String, PlatformGameData> platformGameDataByPlatformName = new HashMap<>();

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

	public Map<String, PlatformGameData> getPlatformGameDataByPlatformName() {
		return platformGameDataByPlatformName;
	}

	public void setPlatformGameDataByPlatformName(Map<String, PlatformGameData> platformGameDataByPlatformName) {
		this.platformGameDataByPlatformName = platformGameDataByPlatformName;
	}
}
