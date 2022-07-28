package io.krystof.launchboxutils.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GameTitleToLBFileNameConverterTest {

	GameTitleToLBFileNameConverter converter = new GameTitleToLBFileNameConverter();

	@Test
	void test() {
		assertEquals("Tom & Jerry_ Satan_s Hollow! - (Game _4)",
				converter.apply("Tom & Jerry: Satan's Hollow! - (Game #4)"));
	}

}
