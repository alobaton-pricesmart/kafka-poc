package com.co.kafkapoc.kafka.basic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.dto.MessageDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer
{
	@Value("${kafka.topics.my-topic}")
	private String topic;

	@KafkaListener(topics = "${kafka.topics.my-topic}", containerFactory = "kafkaListenerContainerFactory")
	public void consume(@Payload GenericMessage<MessageDto> message, @Headers MessageHeaders messageHeaders)
	{
		MessageDto messageDto = message.getPayload();
		if (messageDto == null)
		{
			log.error("empty message!");
		}
		else
		{
			log.info("message received from " + topic + ": " + messageDto.toString());
		}
	}
}
