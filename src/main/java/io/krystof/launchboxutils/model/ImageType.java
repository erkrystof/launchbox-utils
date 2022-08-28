package io.krystof.launchboxutils.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ImageType {

	BOX_FRONT("Box - Front"), GOG_POSTER("GOG Poster");

	private final String pathName;

	private static final Map<String, ImageType> mMap = Collections.unmodifiableMap(initializeMapping());

	private ImageType(String pathName) {
		this.pathName = pathName;
	}

	public static ImageType getByPathName(String pathName) {
		return mMap.get(pathName);
	}

	private static Map<String, ImageType> initializeMapping() {
		Map<String, ImageType> mMap = new HashMap<String, ImageType>();
		for (ImageType s : ImageType.values()) {
			mMap.put(s.getPathName(), s);
		}
		return mMap;
	}

	public String getPathName() {
		return pathName;
	}

}
