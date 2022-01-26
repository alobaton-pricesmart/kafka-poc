package com.co.kafkapoc.kafka.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.dto.TwitDto;
import com.co.kafkapoc.mapper.TwitMapper;
import com.co.kafkapoc.model.Twit;
import com.co.kafkapoc.services.elastic.TwitService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TwitConsumer
{

	@Value("${kafka.topics.twit-topic}")
	private String topic;

	@Autowired
	private TwitService twitService;

	@Autowired
	private TwitMapper twitMapper;

	@KafkaListener(topics = "${kafka.topics.twit-topic}", containerFactory = "kafkaListenerContainerFactory")
	public void consume(@Payload GenericMessage<TwitDto> message, @Headers MessageHeaders messageHeaders)
	{
		TwitDto twitDto = message.getPayload();
		if (twitDto == null)
		{
			log.error("empty twit!");
		}
		else
		{
			log.info("message eceived from " + topic + ": " + twitDto.toString());
			Twit twit = twitService.save(twitMapper.dtoToEneity(twitDto));
			log.info("twit saved: " + twit.toString());
		}
	}

}
