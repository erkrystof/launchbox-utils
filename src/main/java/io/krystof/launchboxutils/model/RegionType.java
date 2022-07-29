package io.krystof.launchboxutils.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum RegionType {

	ROOT("ROOT"), UNITED_STATES("United States"), NORTH_AMERICA("North America"), UNITED_KINGDOM("United Kingdom"),
	WORLD("World");

	private final String pathName;

	private static final Map<String, RegionType> mMap = Collections.unmodifiableMap(initializeMapping());

	private RegionType(String pathName) {
		this.pathName = pathName;
	}

	public static RegionType getByPathName(String pathName) {
		return mMap.get(pathName);
	}

	private static Map<String, RegionType> initializeMapping() {
		Map<String, RegionType> mMap = new HashMap<String, RegionType>();
		for (RegionType s : RegionType.values()) {
			mMap.put(s.getPathName(), s);
		}
		return mMap;
	}

	public String getPathName() {
		return pathName;
	}

}
