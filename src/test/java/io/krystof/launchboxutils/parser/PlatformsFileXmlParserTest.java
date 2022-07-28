package io.krystof.launchboxutils.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import io.krystof.launchboxutils.dto.xml.PlatformFileXmlDto;

class PlatformsFileXmlParserTest extends PlatformsFileXmlParser {
	private static final Logger LOG = LoggerFactory.getLogger(PlatformsFileXmlParserTest.class);

	@Test
	void test() throws URISyntaxException, StreamReadException, DatabindException, IOException {
		PlatformsFileXmlParser parser = new PlatformsFileXmlParser();
		Path path = Paths.get(getClass().getResource("/Data/Platforms/Commodore 64.xml").toURI());
		PlatformFileXmlDto platformFileDto = parser.parse(path);

		platformFileDto.getGames().forEach(game -> {
			LOG.info("Game: {}",game);
		});

		platformFileDto.getCustomFields().forEach(custom -> {
			LOG.info("Custom Field: {}", custom);
		});

	}

}
