package io.krystof.launchboxutils.dto.xml;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

@AutoProperty
public class GameXmlDto {

	@JsonProperty("Title")
	private String title;

	@JsonProperty("ID")
	private String id;

	@JsonProperty("Platform")
	private String platform;

	@JsonProperty("Publisher")
	private String publisher;

	@JsonProperty("Developer")
	private String developer;

	@JsonProperty("DatabaseID")
	private String databaseId;

	@JsonProperty("WikipediaURL")
	private String wikipediaUrl;

	@JsonProperty("VideoUrl")
	private String videoUrl;

	@JsonProperty("Genre")
	private String genreStringRaw;

	@JsonProperty("Series")
	private String series;

	@JsonProperty("Notes")
	private String notes;

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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenreStringRaw() {
		return genreStringRaw;
	}

	public void setGenreStringRaw(String genreStringRaw) {
		this.genreStringRaw = genreStringRaw;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}
}
