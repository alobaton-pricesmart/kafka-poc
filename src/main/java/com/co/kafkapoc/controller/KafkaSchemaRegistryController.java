package com.co.kafkapoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.kafkapoc.dto.TestTypesDto;
import com.co.kafkapoc.kafka.schemaregistry.KafkaSchemaRegistryProducer;

@RestController
@RequestMapping("/kafka-schema-registry")
public class KafkaSchemaRegistryController
{
	@Autowired
	private KafkaSchemaRegistryProducer kafkaSchemaRegistryProducer;

	@PostMapping(path = "/produce")
	public void produce(@RequestBody TestTypesDto dto)
	{
		kafkaSchemaRegistryProducer.produce(dto);
	}

}
