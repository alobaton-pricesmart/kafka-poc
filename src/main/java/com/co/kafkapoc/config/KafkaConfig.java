package com.co.kafkapoc.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.co.kafkapoc.constants.KafkapocConstants;
import com.co.kafkapoc.model.MessageDto;

import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;

@Configuration
@EnableKafka
public class KafkaConfig
{
	@Value("${kafka.bootstrap.url}")
	String bootstrapServersUrl;

	@Value("${kafka.groupId}")
	String groupId;

	@Value("${kafka.schemaRegistryUrl}")
	String schemaRegistryUrl;

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory()
	{
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(getProperties(true)));
		return factory;
	}

	@Bean
	public KafkaTemplate<String, String> kafkaProducerContainerFactory()
	{
		return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(getProperties(false)));
	}

	private Map<String, Object> getProperties(Boolean isConsumer)
	{
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersUrl);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(KafkapocConstants.SCHEMA_REGISTRY_URL, schemaRegistryUrl);

		if (isConsumer)
		{
			props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaJsonSchemaDeserializer.class);
			props.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, MessageDto.class);
			props.put(KafkapocConstants.JSON_FAIL_INVALID_SCHEMA, true);
		}
		else
		{
			props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class);
		}
		return props;
	}
}
