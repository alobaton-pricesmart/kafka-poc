package com.co.kafkapoc.kafka;

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
import com.co.kafkapoc.model.MessageDto;
import com.co.kafkapoc.model.MessageDynamicDto;

import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer;

@Component
public class KafkaDynamicProducer
{

	@Value("${kafka.bootstrap.url}")
	String bootstrapServersUrl;

	@Value("${kafka.groupId}")
	String groupId;

	@Value("${kafka.schemaRegistryUrl}")
	String schemaRegistryUrl;

	public void produce(MessageDynamicDto data)
	{
		final ConcurrentMessageListenerContainer<String, String> container = createContainer(data.getTopic());

		KafkaDynamicListener listener = new KafkaDynamicListener();		
		container.setupMessageListener(listener);
		container.start();
	}

	ConcurrentMessageListenerContainer<String, String> createContainer(String topic)
	{
		final ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
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
		props.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, MessageDto.class);
		props.put(KafkapocConstants.JSON_FAIL_INVALID_SCHEMA, true);
		return props;
	}
}
