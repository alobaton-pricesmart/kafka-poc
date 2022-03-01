package com.co.kafkapoc.exception.handler;

import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomErrorHandlingDeserializer<T> extends ErrorHandlingDeserializer<T>
{
	@Override
	public T deserialize(String topic, byte[] data)
	{
		log.info("data=" + new String(data));
		return super.deserialize(topic, data);
	}

	@Override
	public T deserialize(String topic, Headers headers, byte[] data)
	{
		log.info("data=" + new String(data));
		return super.deserialize(topic, headers, data);
	}

}
