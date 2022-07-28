package io.krystof.launchboxutils.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.launchboxutils.dto.xml.PlatformFileXmlDto;
import io.krystof.launchboxutils.model.PlatformGameData;
import io.krystof.launchboxutils.parser.PlatformsFileXmlParser;

class PlatformXmlDtoToPlatformGameDataConverterTest {

	PlatformXmlDtoToPlatformGameDataConverter converter = new PlatformXmlDtoToPlatformGameDataConverter();
	private static final Logger LOG = LoggerFactory.getLogger(PlatformXmlDtoToPlatformGameDataConverterTest.class);

	@Test
	void test() throws URISyntaxException {
		PlatformsFileXmlParser parser = new PlatformsFileXmlParser();
		Path path = Paths.get(getClass().getResource("/Data/Platforms/Commodore 64.xml").toURI());
		PlatformFileXmlDto platformFileDto = parser.parse(path);
		PlatformGameData platformGameData = converter.apply(platformFileDto, path.getFileName().toString());
		assertEquals("Commodore 64.xml", platformGameData.getPlatformName());
		platformGameData.getGames().forEach(game -> {
			LOG.info("Game: {}\t{}", game.getTitle(), game.getSeries());
		});

	}

}
