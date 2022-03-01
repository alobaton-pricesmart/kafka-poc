package com.co.kafkapoc.util;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaJsonSchemaJacksonUtilTest
{

	@Test
	public void testLocalDateTimeSerializerDeserializer() throws IOException
	{
		LocalDateTime expected = LocalDateTime.now();

		JsonFactory factory = new JsonFactory();
		StringWriter writer = new StringWriter();
		JsonGenerator generator = factory.createGenerator(writer);

		KafkaJsonSchemaJacksonUtil.LocalDateTimeSerializer serializer = new KafkaJsonSchemaJacksonUtil.LocalDateTimeSerializer();
		serializer.serialize(expected, generator, Mockito.mock(SerializerProvider.class));

		generator.flush();
		generator.close();

		String serialized = writer.toString();
		log.info("serialized=" + serialized);

		JsonParser jsonParser = factory.createParser(serialized);

		writer.flush();
		writer.close();

		jsonParser.nextToken();

		KafkaJsonSchemaJacksonUtil.LocalDateTimeDeserializer deserializer = new KafkaJsonSchemaJacksonUtil.LocalDateTimeDeserializer();
		LocalDateTime result = deserializer.deserialize(jsonParser, Mockito.mock(DeserializationContext.class));

		log.info("deserialized=" + result);

		Assertions.assertEquals(expected, result);
	}

	@Test
	public void testLocalDateSerializerDeserializer() throws IOException
	{
		LocalDate expected = LocalDate.now();

		JsonFactory factory = new JsonFactory();
		StringWriter writer = new StringWriter();
		JsonGenerator generator = factory.createGenerator(writer);

		KafkaJsonSchemaJacksonUtil.LocalDateSerializer serializer = new KafkaJsonSchemaJacksonUtil.LocalDateSerializer();
		serializer.serialize(expected, generator, Mockito.mock(SerializerProvider.class));

		generator.flush();
		generator.close();

		String serialized = writer.toString();
		log.info("serialized=" + serialized);

		JsonParser jsonParser = factory.createParser(writer.toString());

		writer.flush();
		writer.close();

		jsonParser.nextToken();

		KafkaJsonSchemaJacksonUtil.LocalDateDeserializer deserializer = new KafkaJsonSchemaJacksonUtil.LocalDateDeserializer();
		LocalDate result = deserializer.deserialize(jsonParser, Mockito.mock(DeserializationContext.class));

		log.info("deserialized=" + result);

		Assertions.assertEquals(expected, result);
	}

	@Test
	public void testLocalTimeSerializerDeserializer() throws IOException
	{
		LocalTime expected = LocalTime.now();

		JsonFactory factory = new JsonFactory();
		StringWriter writer = new StringWriter();
		JsonGenerator generator = factory.createGenerator(writer);

		KafkaJsonSchemaJacksonUtil.LocalTimeSerializer serializer = new KafkaJsonSchemaJacksonUtil.LocalTimeSerializer();
		serializer.serialize(expected, generator, Mockito.mock(SerializerProvider.class));

		generator.flush();
		generator.close();

		String serialized = writer.toString();
		log.info("serialized=" + serialized);

		JsonParser jsonParser = factory.createParser(writer.toString());

		writer.flush();
		writer.close();

		jsonParser.nextToken();

		KafkaJsonSchemaJacksonUtil.LocalTimeDeserializer deserializer = new KafkaJsonSchemaJacksonUtil.LocalTimeDeserializer();
		LocalTime result = deserializer.deserialize(jsonParser, Mockito.mock(DeserializationContext.class));

		log.info("deserialized=" + result);

		Assertions.assertEquals(expected, result);
	}

}
