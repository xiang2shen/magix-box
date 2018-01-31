package com.magicbox.support;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DateObjectMapper extends MappingJackson2HttpMessageConverter {

	public DateObjectMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
