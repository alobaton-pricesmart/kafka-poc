package com.co.kafkapoc.kafka.dynamic;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.constants.KafkapocConstants;
import com.co.kafkapoc.dto.MessageDto;

import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;

@Component
public class KafkaDynamicProducer
{

	@Value("${kafka.bootstrap.url}")
	String bootstrapServersUrl;

	@Value("${kafka.groupId}")
	String groupId;

	@Value("${kafka.schemaRegistryUrl}")
	String schemaRegistryUrl;
	
	@Value("${kafka.autoRegisterSchemas}")
	private boolean autoRegisterSchemas;

	public void produce(String topic, MessageDto data)
	{
		Message<MessageDto> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, topic).build();

		KafkaTemplate<String, MessageDto> kafkaTemplate = createKafkaTemplate();

		kafkaTemplate.send(message);
	}

	private KafkaTemplate<String, MessageDto> createKafkaTemplate()
	{
		return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(getDynamicProducerProperties()));
	}

	private Map<String, Object> getDynamicProducerProperties()
	{
		final Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersUrl);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(KafkapocConstants.SCHEMA_REGISTRY_URL, schemaRegistryUrl);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class);
		props.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, MessageDto.class);
		props.put(KafkapocConstants.JSON_FAIL_INVALID_SCHEMA, true);
		props.put(KafkapocConstants.AUTO_REGISTER_SCHEMAS, autoRegisterSchemas);
		return props;
	}
}
