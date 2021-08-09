package com.co.kafkapoc.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.model.MessageDto;

@Component
public class KafkaConsumer
{
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "${kafka.topic}", containerFactory = "kafkaListenerContainerFactory")
	public void consume(@Payload GenericMessage<MessageDto> message, @Headers MessageHeaders messageHeaders)
	{
		MessageDto messageDto = message.getPayload();
		if (messageDto == null)
		{
			LOGGER.error("empty message!");
		}
		else
		{
			LOGGER.info(messageDto.getMessage());
		}
	}
}
