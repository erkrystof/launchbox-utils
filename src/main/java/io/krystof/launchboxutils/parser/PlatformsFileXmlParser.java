package io.krystof.launchboxutils.parser;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.krystof.launchboxutils.dto.xml.PlatformFileXmlDto;
import io.krystof.launchboxutils.exception.GenericException;

public class PlatformsFileXmlParser {

	private static final Logger LOG = LoggerFactory.getLogger(PlatformsFileXmlParser.class);

	private static final XmlMapper xmlMapper = (XmlMapper) new XmlMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public PlatformFileXmlDto parse(Path platformFile)  {
		LOG.info("Parse path: {}", platformFile);
		try {
			return xmlMapper.readValue(platformFile.toFile(), PlatformFileXmlDto.class);
		} catch (IOException e) {
			throw new GenericException("Error parsing file: " + platformFile, e);
		}
	}


}
