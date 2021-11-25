package com.co.kafkapoc.kafka.dynamic;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.constants.KafkapocConstants;
import com.fasterxml.jackson.databind.JsonNode;

import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer;

@Component
public class KafkaDynamicConsumer
{

	@Value("${kafka.bootstrap.url}")
	String bootstrapServersUrl;

	@Value("${kafka.groupId}")
	String groupId;

	@Value("${kafka.schemaRegistryUrl}")
	String schemaRegistryUrl;

	/**
	 * Start a dynamic consumer.
	 * @param topic Dynamic consumer topic.
	 */
	public void start(String topic)
	{
		final ConcurrentMessageListenerContainer<String, JsonNode> container = createContainer(topic);

		KafkaDynamicListener listener = new KafkaDynamicListener();		
		container.setupMessageListener(listener);
		container.start();
	}

	ConcurrentMessageListenerContainer<String, JsonNode> createContainer(String topic)
	{
		final ConcurrentKafkaListenerContainerFactory<String, JsonNode> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(getDynamicConsumerProperties()));
		return factory.createContainer(topic);
	}

	private Map<String, Object> getDynamicConsumerProperties()
	{
		final Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersUrl);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(KafkapocConstants.SCHEMA_REGISTRY_URL, schemaRegistryUrl);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaJsonSchemaDeserializer.class);
		props.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, JsonNode.class);
		props.put(KafkapocConstants.JSON_FAIL_INVALID_SCHEMA, true);
		return props;
	}
}
