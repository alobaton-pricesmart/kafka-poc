package com.co.kafkapoc.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.confluent.kafka.serializers.jackson.Jackson;

/**
 * This class is intended to work as utility to map POJOs the same way the KafkaJsonSchema serializers and deserializers does so we can safely send
 * messages through the stream.
 *
 */
public class KafkaJsonSchemaJacksonUtil
{
	/**
	 * Allow us to map LocalDate, LocalDateTime and LocalTime as array of integers. This is needed if you are working with KafkaJsonSchemaSerializer or
	 * KafkaJsonSchemaDeserializer because the {@link io.confluent.kafka.schemaregistry.json.JsonSchemaUtils} ObjectMapper can not be overwritten so we
	 * must use the same ObjectMapper in our side so the POJOs are parsed the same way.
	 */
	public static final ObjectMapper objectMapper = Jackson.newObjectMapper();

	public static class LocalTimeSerializer extends JsonSerializer<LocalTime>
	{
		@Override
		public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException
		{
			gen.writeRawValue(objectMapper.writeValueAsString(value));
		}
	}

	public static class LocalTimeDeserializer extends JsonDeserializer<LocalTime>
	{
		@Override
		public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
		{
			return objectMapper.readValue(readArray(jsonParser), LocalTime.class);
		}
	}

	public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>
	{
		@Override
		public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException
		{
			gen.writeRawValue(objectMapper.writeValueAsString(value));
		}
	}

	public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>
	{
		@Override
		public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
		{
			return objectMapper.readValue(readArray(jsonParser), LocalDateTime.class);
		}
	}

	public static class LocalDateSerializer extends JsonSerializer<LocalDate>
	{
		@Override
		public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException
		{
			gen.writeRawValue(objectMapper.writeValueAsString(value));
		}
	}

	public static class LocalDateDeserializer extends JsonDeserializer<LocalDate>
	{
		@Override
		public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
		{
			return objectMapper.readValue(readArray(jsonParser), LocalDate.class);
		}
	}

	private static String readArray(JsonParser jsonParser) throws IOException
	{
		List<String> list = new ArrayList<>();
		JsonToken jsonToken = null;

		do
		{
			jsonToken = jsonParser.currentToken();
			if (JsonToken.VALUE_NUMBER_INT.equals(jsonToken))
			{
				String value = jsonParser.getValueAsString();
				list.add(value);
			}

			jsonToken = jsonParser.nextToken();
		}
		while (!jsonParser.isClosed());

		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(String.join(",", list));
		builder.append("]");
		return builder.toString();
	}
}
