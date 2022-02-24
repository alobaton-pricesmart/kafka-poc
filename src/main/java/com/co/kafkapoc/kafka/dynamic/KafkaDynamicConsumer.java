package com.co.kafkapoc.kafka.dynamic;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.adapter.RetryingMessageListenerAdapter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.constants.KafkaPocConstants;
import com.fasterxml.jackson.databind.JsonNode;

import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer;

@Component
public class KafkaDynamicConsumer
{

	@Value("${kafka.bootstrap.url}")
	private String bootstrapServersUrl;

	@Value("${kafka.groupId}")
	private String groupId;

	@Value("${kafka.schemaRegistryUrl}")
	private String schemaRegistryUrl;

	@Autowired
	private RetryTemplate retryTemplate;

	/**
	 * Start a dynamic consumer.
	 * 
	 * @param topic
	 *          Dynamic consumer topic.
	 */
	public void start(String topic)
	{
		final ConcurrentMessageListenerContainer<String, JsonNode> container = createContainer(topic);

		KafkaDynamicListener listener = new KafkaDynamicListener();
		RetryingMessageListenerAdapter<String, JsonNode> retryingListener = new RetryingMessageListenerAdapter<>(listener, retryTemplate);
		container.setupMessageListener(retryingListener);
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
		props.put(KafkaPocConstants.SCHEMA_REGISTRY_URL, schemaRegistryUrl);
		props.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, JsonNode.class);
		props.put(KafkaPocConstants.JSON_FAIL_INVALID_SCHEMA, true);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
		props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, KafkaJsonSchemaDeserializer.class);

		return props;
	}
}
