package com.co.kafkapoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.kafkapoc.kafka.KafkaDynamicProducer;
import com.co.kafkapoc.kafka.KafkaProducer;
import com.co.kafkapoc.model.MessageDto;
import com.co.kafkapoc.model.MessageDynamicDto;

@RestController
@RequestMapping("/kafka")
public class KafkaController
{

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private KafkaDynamicProducer kafkaDynamicProducer;

	@PostMapping(path = "/produce")
	public void produce(@RequestBody MessageDto dto)
	{
		kafkaProducer.produce(dto);
	}

	@PostMapping(path = "/produce/dynamic")
	public void produceDynamic(@RequestBody MessageDynamicDto dto)
	{
		kafkaDynamicProducer.produce(dto);
	}
}
