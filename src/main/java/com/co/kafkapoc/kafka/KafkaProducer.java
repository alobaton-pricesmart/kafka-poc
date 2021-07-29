package com.co.kafkapoc.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.model.MessageDto;

@Component
public class KafkaProducer
{
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.topic}")
	private String kafkaTopic;

	public void produce(MessageDto data)
	{
		Message<MessageDto> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, kafkaTopic).build();

		kafkaTemplate.send(message);
	}
}
