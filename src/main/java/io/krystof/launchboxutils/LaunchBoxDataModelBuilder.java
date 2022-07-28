package io.krystof.launchboxutils;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;

import io.krystof.launchboxutils.converters.PlatformXmlDtoToPlatformGameDataConverter;
import io.krystof.launchboxutils.model.LaunchBoxDataModel;
import io.krystof.launchboxutils.parser.PlatformsFileXmlParser;

public class LaunchBoxDataModelBuilder {

	private Path launchBoxRootPath;

	public LaunchBoxDataModelBuilder withLaunchBoxRootPath(Path p) {
		this.launchBoxRootPath = p;
		return this;
	}

	public LaunchBoxDataModel build() {
		PlatformsFileXmlParser platformsFileXmlParser = new PlatformsFileXmlParser();
		PlatformXmlDtoToPlatformGameDataConverter platformXmlDtoToPlatformGameDataConverter = new PlatformXmlDtoToPlatformGameDataConverter();

		LaunchBoxDataModel dataModel = new LaunchBoxDataModel();
		File[] platformFiles = launchBoxRootPath.resolve("Data/Platforms").toFile()
				.listFiles((FilenameFilter) (dir, name) -> name.toLowerCase().endsWith(".xml"));
		for (File platformFile : platformFiles) {
			String platformName = StringUtils.substringBeforeLast(platformFile.getName(), ".");
			dataModel.getPlatformGameDataByPlatformName().put(platformName, platformXmlDtoToPlatformGameDataConverter
					.apply(platformsFileXmlParser.parse(platformFile.toPath()), platformName));
		}
		return dataModel;
	}

}
