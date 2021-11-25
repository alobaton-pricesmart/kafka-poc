package com.co.kafkapoc.kafka.basic;

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
	private KafkaTemplate<String, MessageDto> kafkaTemplate;

	@Value("${kafka.topic}")
	private String topic;

	public void produce(MessageDto data)
	{
		Message<MessageDto> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, topic).build();

		kafkaTemplate.send(message);
	}
}
