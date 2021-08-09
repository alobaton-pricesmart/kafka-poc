package com.co.kafkapoc.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.model.MessageDto;

@Component
public class KafkaDynamicProducer
{

	@Autowired
	private KafkaTemplate<String, MessageDto> kafkaTemplate;

	public void produce(String topic, MessageDto data)
	{
		Message<MessageDto> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, topic).build();

		kafkaTemplate.send(message);
	}
}
