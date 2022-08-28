package io.krystof.launchboxutils.converters;

import java.util.function.Function;

import org.apache.commons.lang3.RegExUtils;

public class GameTitleToLBFileNameConverter implements Function<String, String> {

	@Override
	public String apply(String gameTitle) {
		return RegExUtils.replaceAll(gameTitle, "[^a-zA-Z0-9-_\\.\\(\\)\\ \\-\\&\\!\\$\\,\\+\\[\\]]", "_");
	}
}