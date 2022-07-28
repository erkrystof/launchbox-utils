package io.krystof.launchboxutils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.launchboxutils.model.LaunchBoxDataModel;

class LaunchBoxDataModelBuilderTest {
	private static final Logger LOG = LoggerFactory.getLogger(LaunchBoxDataModelBuilderTest.class);

	@Test
	void test() throws URISyntaxException {
		Path path = Paths.get(getClass().getResource("/").toURI());
		LaunchBoxDataModel model = new LaunchBoxDataModelBuilder().withLaunchBoxRootPath(path).build();

		LOG.info("Platforms count: {}", model.getPlatformGameDataByPlatformName().size());
		model.getPlatformGameDataByPlatformName().entrySet().forEach(entry -> {
			LOG.info("Games for Platform: {}, {}", entry.getKey(), entry.getValue().getGames().size());
			assertEquals(entry.getKey(), entry.getValue().getPlatformName());
		});
		
	}

}