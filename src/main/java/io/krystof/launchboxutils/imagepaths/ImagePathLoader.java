package io.krystof.launchboxutils.imagepaths;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.launchboxutils.exception.GenericException;

public class ImagePathLoader {
	private static final Logger LOG = LoggerFactory.getLogger(ImagePathLoader.class);

	public Map<String, List<ImagePathData>> loadImagePathData(Path launchBoxRootPath,
			List<String> imageTypeOrderPreference, List<String> regionOrderPreference) {
		Map<String, List<ImagePathData>> imagePathsToGameFileName = new HashMap<>();
		try {

			Files.walkFileTree(launchBoxRootPath.resolve("Images"), new FileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					// If we're an actual platform dir, continue. Otherwise, ignore directories like
					// Badges and None and Platforms, etc.
					if (StringUtils.containsAny(dir.getFileName().toString(), "Badges", "None", "Platforms")) {
						return FileVisitResult.SKIP_SUBTREE;
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String fileNameForGame = file.getFileName().toString();
					fileNameForGame = StringUtils.substringBeforeLast(fileNameForGame, "-");
					ImagePathData imagePathData = generateImagePathData(file, launchBoxRootPath);
					imagePathsToGameFileName.compute(fileNameForGame, (k, v) -> v == null ? v = new ArrayList<>() : v)
							.add(imagePathData);
					return FileVisitResult.CONTINUE;
				}

				private ImagePathData generateImagePathData(Path file, Path launchBoxRootPath) {
					ImagePathData ipd = new ImagePathData();
					ipd.setRelativePath(launchBoxRootPath.relativize(file));
					// First one is Images. Second is platform, Third is type.
					// Fourth *might be* Region, if not, it's a file.
					// If fourth is region, fifth is a file.

					ipd.setPlatformString(ipd.getRelativePath().getName(1).toString());
					ipd.setImageTypeString(ipd.getRelativePath().getName(2).toString());
					if (ipd.getRelativePath().getNameCount() == 5) {
						ipd.setRegionTypeString(Optional.of(ipd.getRelativePath().getName(3).toString()));
					}
					return ipd;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			throw new GenericException("Unable to walk for images in path: " + launchBoxRootPath, e);
		}

		imagePathsToGameFileName.keySet().forEach(key -> {
			LOG.info("Image Paths for Game File: {} : {}", key, imagePathsToGameFileName.get(key).size());

			// Sort by image type, then by region underneath.
			Collections.sort(imagePathsToGameFileName.get(key), (o1, o2) -> {
				int o1ImageTypeDefinedOrder = imageTypeOrderPreference.indexOf(o1.getImageTypeString());
				int o2ImageTypeDefinedOrder = imageTypeOrderPreference.indexOf(o2.getImageTypeString());
				if (o1ImageTypeDefinedOrder == -1 && o2ImageTypeDefinedOrder >= 0) {
					return 1;
				}
				if (o1ImageTypeDefinedOrder >= 0 && o2ImageTypeDefinedOrder == -1) {
					return -1;
				}
				if (o1ImageTypeDefinedOrder == -1 && o1ImageTypeDefinedOrder == -1) {
					int stringSort = StringUtils.compare(o1.getImageTypeString(), o2.getImageTypeString());
					if (stringSort != 0) {
						return stringSort;
					} else {
						// They are the same!
					}
				}
				if (o1ImageTypeDefinedOrder != o2ImageTypeDefinedOrder) {
					return Integer.compare(o1ImageTypeDefinedOrder, o2ImageTypeDefinedOrder);
				}

				// If we're here, the image comparison was a wash. They're the same effective
				// value. So now we look at region.

				String o1RegionString = o1.getRegionTypeString().orElseGet(() -> "ROOT");
				String o2RegionString = o2.getRegionTypeString().orElseGet(() -> "ROOT");

				int o1RegionTypeDefinedOrder = regionOrderPreference
						.indexOf(o1RegionString);
				int o2RegionTypeDefinedOrder = regionOrderPreference
						.indexOf(o2RegionString);
				if (o1RegionTypeDefinedOrder == -1 && o2RegionTypeDefinedOrder >= 0) {
					return 1;
				}
				if (o1RegionTypeDefinedOrder >= 0 && o2RegionTypeDefinedOrder == -1) {
					return -1;
				}
				if (o1RegionTypeDefinedOrder == -1 && o2RegionTypeDefinedOrder == -1) {
					int stringSort = StringUtils.compare(o1RegionString, o2RegionString);
					if (stringSort != 0) {
						return stringSort;
					} else {
						// They are the same!
					}
				}
				if (o1RegionTypeDefinedOrder != o2RegionTypeDefinedOrder) {
					return Integer.compare(o1RegionTypeDefinedOrder, o2RegionTypeDefinedOrder);
				}

				// If we're here, we're effectively the same for both image type and region. Now
				// we just sort by the known filename.

				return StringUtils.compare(o1.getRelativePath().getFileName().toString(),
						o2.getRelativePath().getFileName().toString());

			});

			imagePathsToGameFileName.get(key).forEach(imagePathData -> {
				LOG.trace("\t\t{}", imagePathData);
			});

		});

		return imagePathsToGameFileName;
	}

}
