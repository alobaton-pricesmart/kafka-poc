package com.co.kafkapoc.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	public void produce()
	{
		TestTypesDto testTypesDto = new TestTypesDto();
		testTypesDto.setArrayRequired(Arrays.asList(1));
		testTypesDto.setBooleanRequired(true);
		testTypesDto.setLocalDateTimeRequired(LocalDateTime.now());
		testTypesDto.setLocalDateRequired(LocalDate.now());
		testTypesDto.setLocalTimeRequired(LocalTime.now());
		testTypesDto.setIntegerRequired(1);
		testTypesDto.setNumberRequired(1.5);
		testTypesDto.setStringRequired("test");
		kafkaSchemaRegistryProducer.produce(testTypesDto);
	}

}
