package com.co.kafkapoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.kafkapoc.kafka.KafkaProducer;
import com.co.kafkapoc.model.MessageDto;

@RestController
@RequestMapping("/kafka")
public class KafkaController
{

	@Autowired
	private KafkaProducer kafkaProducer;

	@PostMapping(path = "/produce")
	public void produce(@RequestBody MessageDto messageDto)
	{
		kafkaProducer.produce(messageDto);
	}
}
