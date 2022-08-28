package io.krystof.launchboxutils;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.launchboxutils.converters.GameTitleToLBFileNameConverter;
import io.krystof.launchboxutils.converters.PlatformXmlDtoToPlatformGameDataConverter;
import io.krystof.launchboxutils.imagepaths.ImagePathData;
import io.krystof.launchboxutils.imagepaths.ImagePathLoader;
import io.krystof.launchboxutils.model.ImageType;
import io.krystof.launchboxutils.model.LaunchBoxDataModel;
import io.krystof.launchboxutils.model.RegionType;
import io.krystof.launchboxutils.parser.PlatformsFileXmlParser;

/**
 * Loads from a root directory of launch box (e.g. c:\launchbox) into various
 * object files. Image files are attached to the Game model object, ordered by
 * preferences set into the builder (there are some defaults)
 */
public class LaunchBoxDataModelBuilder {

	private Path launchBoxRootPath;

	private static final Logger LOG = LoggerFactory.getLogger(LaunchBoxDataModelBuilder.class);

	List<String> imageTypeOrderPreference = Arrays.asList(ImageType.BOX_FRONT.getPathName(),
			ImageType.GOG_POSTER.getPathName());

	List<String> regionOrderPreference = Arrays.asList(RegionType.ROOT.getPathName(),
			RegionType.UNITED_STATES.getPathName(),
			RegionType.NORTH_AMERICA.getPathName(), RegionType.UNITED_STATES.getPathName(),
			RegionType.UNITED_KINGDOM.getPathName());

	public LaunchBoxDataModelBuilder withLaunchBoxRootPath(Path p) {
		this.launchBoxRootPath = p;
		return this;
	}

	/**
	 * Specify a list of region values for sorting. Any that don't fall into the
	 * regions specified are sorted alphabetically (e.g. leftovers) and placed at
	 * the end of the result.
	 * 
	 * @see RegionType
	 * @param regionOrderPreference
	 * @return
	 */
	public LaunchBoxDataModelBuilder withRegionOrderPreference(List<String> regionOrderPreference) {
		this.regionOrderPreference = regionOrderPreference;
		return this;
	}

	/**
	 * Specify a list of image types for sorting. Any that don't fall into the image
	 * types specified are sorted alphabetically (e.g. leftovers) and placed at the
	 * end of the result.
	 * 
	 * @see ImageType
	 * @param imageTypeOrderPreference
	 * @return
	 */
	public LaunchBoxDataModelBuilder withImageTypeOrderPreference(List<String> imageTypeOrderPreference) {
		this.imageTypeOrderPreference = imageTypeOrderPreference;
		return this;
	}

	public LaunchBoxDataModel build() {

		LaunchBoxDataModel dataModel = new LaunchBoxDataModel();

		LOG.info("Loading platform files");
		loadPlatformFiles(dataModel);

		LOG.info("Loading image references");
		loadImageReferences(dataModel);

		LOG.info("LB data model complete.");

		return dataModel;
	}

	private void loadImageReferences(LaunchBoxDataModel dataModel) {

		GameTitleToLBFileNameConverter gameTitleToLBFileNameConverter = new GameTitleToLBFileNameConverter();

		ImagePathLoader imagePathLoader = new ImagePathLoader();

		Map<String, List<ImagePathData>> imagePathsToGameFileName = imagePathLoader.loadImagePathData(launchBoxRootPath,
				imageTypeOrderPreference, regionOrderPreference);

		dataModel.getPlatformGameDataByPlatformName().values().forEach(platform -> {
			String platformName = platform.getPlatformName();
			LOG.info("Applying images to loaded game data for platform: {}", platformName);
			platform.getGames().forEach(game -> {
				String gameFileSafeName = gameTitleToLBFileNameConverter.apply(game.getTitle());
				if (imagePathsToGameFileName.get(gameFileSafeName) == null) {
					LOG.warn("No images found for game title {} (as file name safe string: '{}')", game.getTitle(),
							gameFileSafeName);
				} else {
					for (ImagePathData imagePathData : imagePathsToGameFileName.get(gameFileSafeName)) {
						if (StringUtils.equals(imagePathData.getPlatformString(), platformName)) {
							game.getImagePathData().add(imagePathData);
						}
					}
					LOG.info("Loaded {} platform specific image paths for game: {}", game.getImagePathData().size(),
							game.getTitle());
				}
			});
		});

	}

	private void loadPlatformFiles(LaunchBoxDataModel dataModel) {

		PlatformsFileXmlParser platformsFileXmlParser = new PlatformsFileXmlParser();
		PlatformXmlDtoToPlatformGameDataConverter platformXmlDtoToPlatformGameDataConverter = new PlatformXmlDtoToPlatformGameDataConverter();

		File[] platformFiles = launchBoxRootPath.resolve("Data/Platforms").toFile()
				.listFiles((FilenameFilter) (dir, name) -> name.toLowerCase().endsWith(".xml"));
		for (File platformFile : platformFiles) {
			String platformName = StringUtils.substringBeforeLast(platformFile.getName(), ".");
			dataModel.getPlatformGameDataByPlatformName().put(platformName, platformXmlDtoToPlatformGameDataConverter
					.apply(platformsFileXmlParser.parse(platformFile.toPath()), platformName));
			Collections.sort(dataModel.getPlatformGameDataByPlatformName().get(platformName).getGames(), (g1, g2) -> {
				return StringUtils.compare(g1.getTitle(), g2.getTitle());
			});
		}

	}

}
