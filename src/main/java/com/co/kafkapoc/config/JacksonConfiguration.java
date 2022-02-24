package com.co.kafkapoc.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.co.kafkapoc.util.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfiguration
{

	@Bean
	public JavaTimeModule javaTimeModule()
	{
		return new JavaTimeModule();
	}

	@Bean
	public Jdk8Module jdk8Module()
	{
		return new Jdk8Module();
	}

	public static class LocalTimeSerializer extends JsonSerializer<LocalTime>
	{
		@Override
		public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException
		{
			gen.writeString(DateUtil.formatLocalTime(value));
		}
	}

	public static class LocalTimeDeserializer extends JsonDeserializer<LocalTime>
	{
		@Override
		public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
		{
			return DateUtil.parseLocalTime(jsonParser.getText());
		}
	}

	public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>
	{
		@Override
		public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException
		{
			gen.writeString(DateUtil.formatLocalDate(value));
		}
	}

	public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>
	{
		@Override
		public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
		{
			return DateUtil.parseLocalDate(jsonParser.getText());
		}
	}

	@Bean(name = "encoderModule")
	public Module encoderModule()
	{
		SimpleModule module = new SimpleModule("Module", new Version(1, 0, 0, null, null, null));
		module.addSerializer(LocalTime.class, new LocalTimeSerializer());
		module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
		module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
		module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

		return module;
	}

	@Bean
	@Primary
	public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder()
	{
		return new Jackson2ObjectMapperBuilder().serializerByType(LocalTime.class, new LocalTimeSerializer())
				.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer()).featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.modules(new JavaTimeModule(), encoderModule());
	}
}