package com.co.kafkapoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.kafkapoc.kafka.KafkaDynamicConsumer;
import com.co.kafkapoc.kafka.KafkaDynamicProducer;
import com.co.kafkapoc.kafka.KafkaProducer;
import com.co.kafkapoc.model.MessageDto;

@RestController
@RequestMapping("/kafka")
public class KafkaController
{

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private KafkaDynamicProducer kafkaDynamicProducer;
	
	@Autowired
	private KafkaDynamicConsumer kafkaDynamicConsumer;

	@PostMapping(path = "/produce")
	public void produce(@RequestBody MessageDto dto)
	{
		kafkaProducer.produce(dto);
	}
	
	@PostMapping(path = "/produce/{topic}")
	public void produce(@PathVariable String topic, @RequestBody MessageDto dto)
	{
		kafkaDynamicProducer.produce(topic, dto);
	}

	@PostMapping(path = "/consumer/{topic}")
	public void produceDynamic(@PathVariable String topic)
	{
		kafkaDynamicConsumer.start(topic);
	}
}
