package io.krystof.launchboxutils.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import io.krystof.launchboxutils.imagepaths.ImagePathData;

@AutoProperty
public class Game {

	public static final String CUSTOM_FIELD_KEY_SERIES_INDEX = "SeriesIndex";

	public static final String CUSTOM_FIELD_PORT_IDENTIFIER = "PortIdentifier";

	private String title;

	private String id;

	private String platform;

	private Set<String> publishers = new HashSet<>();

	private Set<String> developers = new HashSet<>();

	private String databaseId;

	private String wikipediaUrl;

	private String videoUrl;

	private Set<String> genres = new HashSet<>();

	private Map<String, String> customFields = new HashMap<>();

	private String series;

	private Integer releaseYear;

	private String notes;

	List<ImagePathData> imagePathData = new ArrayList<>();

	private boolean favorite;

	private Integer starRating;

	@Override
	public boolean equals(Object o) {
		return Pojomatic.equals(this, o);
	}

	@Override
	public int hashCode() {
		return Pojomatic.hashCode(this);
	}

	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}



	public String getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(String databaseId) {
		this.databaseId = databaseId;
	}

	public String getWikipediaUrl() {
		return wikipediaUrl;
	}

	public void setWikipediaUrl(String wikipediaUrl) {
		this.wikipediaUrl = wikipediaUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Map<String, String> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, String> customFields) {
		this.customFields = customFields;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Set<String> getPublishers() {
		return publishers;
	}

	public void setPublishers(Set<String> publishers) {
		this.publishers = publishers;
	}

	public Set<String> getDevelopers() {
		return developers;
	}

	public void setDevelopers(Set<String> developers) {
		this.developers = developers;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public List<ImagePathData> getImagePathData() {
		return imagePathData;
	}

	public void setImagePathData(List<ImagePathData> imagePathData) {
		this.imagePathData = imagePathData;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public Integer getStarRating() {
		return starRating;
	}

	public void setStarRating(Integer starRating) {
		this.starRating = starRating;
	}

}
