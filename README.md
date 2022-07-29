# launchbox-utils
Some utilities to read/scan Launchbox data files.

Primary use is the LaunchBoxDataModelBuilder pointing at a Launchbox install (e.g. c:\launchbox).  It currently loads the following into some model objects:

(Model objects are in `io.krystof.launchboxutils.model`)

1. A list of Game objects mapped by Platform name.
2. Each Game object has a reference to related images for the platform it's attached to.
3. Images are ordered by defaults, can be overridden in the builder methods `withRegionOrderPreference` and `withImageTypeOrderPreference` methods.  Image order is sorted before region.

Example usage:

	void testLocalInstall() throws URISyntaxException {
			Path path = Paths.get("H:\\LaunchBoxForRetroListBuilding");
			LaunchBoxDataModel model = new LaunchBoxDataModelBuilder().withLaunchBoxRootPath(path).build();
	
			LOG.info("Platforms count: {}", model.getPlatformGameDataByPlatformName().size());
			model.getPlatformGameDataByPlatformName().entrySet().forEach(entry -> {
				LOG.info("Games for Platform: {}, {}", entry.getKey(), entry.getValue().getGames().size());
				assertEquals(entry.getKey(), entry.getValue().getPlatformName());
				entry.getValue().getGames().forEach(game -> {
					LOG.info("\t{} will use image type: {} and region: {} and file: {}", game.getTitle(),
							game.getImagePathData().get(0).getImageTypeString(),
							game.getImagePathData().get(0).getRegionTypeString().orElse("N/A"),
							game.getImagePathData().get(0).getRelativePath().getFileName().toString());
				});
			});
	
		}
