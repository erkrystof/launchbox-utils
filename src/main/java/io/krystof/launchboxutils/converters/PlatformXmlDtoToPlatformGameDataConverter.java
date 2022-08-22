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
			game.setDatabaseId(sanitize(gameXmlDto.getDatabaseId()));
			game.setDevelopers(convertStringToSet(gameXmlDto.getDeveloper()));
			game.setId(sanitize(gameXmlDto.getId()));
			game.setNotes(sanitize(gameXmlDto.getNotes()));
			game.setPlatform(sanitize(gameXmlDto.getPlatform()));
			game.setPublishers(convertStringToSet(gameXmlDto.getPublisher()));
			game.setTitle(sanitize(gameXmlDto.getTitle()));
			game.setVideoUrl(sanitize(gameXmlDto.getVideoUrl()));
			game.setWikipediaUrl(sanitize(gameXmlDto.getWikipediaUrl()));
			game.setSeries(sanitize(gameXmlDto.getSeries()));
			game.setGenres(convertStringToSet(gameXmlDto.getGenre()));
			game.setFavorite(gameXmlDto.isFavorite());
			if (StringUtils.isNotBlank(gameXmlDto.getReleaseDate())) {
				game.setReleaseYear(Integer.parseInt(StringUtils.substringBefore(gameXmlDto.getReleaseDate(), "-")));
			}

			platformFileXmlDto.getCustomFields().stream().filter(cf -> cf.getGameId().equals(game.getId()))
					.forEach(cf -> {
						game.getCustomFields().put(cf.getName(), sanitize(cf.getValue()));
					});
			pgd.getGames().add(game);
		}
		return pgd;
	}

	private String sanitize(String string) {
		return StringUtils.isNotEmpty(string) ? StringUtils.trim(string) : null;
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
