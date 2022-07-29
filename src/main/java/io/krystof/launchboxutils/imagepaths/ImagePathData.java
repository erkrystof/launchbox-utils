package io.krystof.launchboxutils.imagepaths;

import java.nio.file.Path;
import java.util.Optional;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class ImagePathData {

	private String platformString;

	private String imageTypeString;

	private Optional<String> regionTypeString = Optional.empty();

	private Path relativePath;

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

	public String getPlatformString() {
		return platformString;
	}

	public void setPlatformString(String platformString) {
		this.platformString = platformString;
	}

	public String getImageTypeString() {
		return imageTypeString;
	}

	public void setImageTypeString(String imageTypeString) {
		this.imageTypeString = imageTypeString;
	}

	public Optional<String> getRegionTypeString() {
		return regionTypeString;
	}

	public void setRegionTypeString(Optional<String> regionTypeString) {
		this.regionTypeString = regionTypeString;
	}

	public Path getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(Path relativePath) {
		this.relativePath = relativePath;
	}
}
