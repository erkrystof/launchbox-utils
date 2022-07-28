package io.krystof.launchboxutils.converters;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

import org.apache.commons.lang3.StringUtils;

import io.krystof.launchboxutils.dto.xml.GameXmlDto;
import io.krystof.launchboxutils.dto.xml.PlatformFileXmlDto;
import io.krystof.launchboxutils.model.Game;
import io.krystof.launchboxutils.model.PlatformGameData;

public class PlatformXmlDtoToPlatformGameDataConverter
		implements BiFunction<PlatformFileXmlDto, String, PlatformGameData> {

	@Override
	public PlatformGameData apply(PlatformFileXmlDto platformFileXmlDto, String fileNameAsPlatform) {
		PlatformGameData pgd = new PlatformGameData();
		pgd.setPlatformName(fileNameAsPlatform);
		for (GameXmlDto gameXmlDto : platformFileXmlDto.getGames()) {
			Game game = new Game();
			game.setDatabaseId(gameXmlDto.getDatabaseId());
			game.setDevelopers(convertStringToSet(gameXmlDto.getDeveloper()));
			game.setId(gameXmlDto.getId());
			game.setNotes(gameXmlDto.getNotes());
			game.setPlatform(gameXmlDto.getPlatform());
			game.setPublishers(convertStringToSet(gameXmlDto.getPublisher()));
			game.setTitle(gameXmlDto.getTitle());
			game.setVideoUrl(gameXmlDto.getVideoUrl());
			game.setWikipediaUrl(gameXmlDto.getWikipediaUrl());
			game.setSeries(gameXmlDto.getSeries());
			game.setGenres(convertStringToSet(gameXmlDto.getGenre()));
			if (StringUtils.isNotBlank(gameXmlDto.getReleaseDate())) {
				game.setReleaseYear(Integer.parseInt(StringUtils.substringBefore(gameXmlDto.getReleaseDate(), "-")));
			}

			platformFileXmlDto.getCustomFields().stream().filter(cf -> cf.getGameId().equals(game.getId()))
					.forEach(cf -> {
						game.getCustomFields().put(cf.getName(), cf.getValue());
					});
			pgd.getGames().add(game);
		}
		return pgd;
	}

	private Set<String> convertStringToSet(String rawString) {
		Set<String> returnValue = new HashSet<>();
		if (StringUtils.isNotEmpty(rawString)) {
			for (String token : rawString.split(";")) {
				returnValue.add(StringUtils.trim(token));
			}
		}
		return returnValue;
	}
}
