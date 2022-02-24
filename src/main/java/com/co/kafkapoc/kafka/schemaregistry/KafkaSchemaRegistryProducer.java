package com.co.kafkapoc.kafka.schemaregistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.dto.TestTypesDto;

@Component
public class KafkaSchemaRegistryProducer
{
	@Autowired
	private KafkaTemplate<String, TestTypesDto> kafkaTemplate;

	@Value("${kafka.topics.test-types-topic}")
	private String topic;

	public void produce(TestTypesDto data)
	{
		Message<TestTypesDto> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, topic).build();

		kafkaTemplate.send(message);
	}

}
