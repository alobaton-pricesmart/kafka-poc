package com.co.kafkapoc.kafka.twitter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.co.kafkapoc.dto.MessageDto;
import com.co.kafkapoc.dto.TwitDto;
import com.co.kafkapoc.services.twitter.TwitterService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TwitProducer
{

	@Autowired
	private KafkaTemplate<String, MessageDto> kafkaTemplate;

	@Value("${kafka.topics.twit-topic}")
	private String topic;

	@Autowired
	private TwitterService twitterService;

	public void produce()
	{
		try
		{
			Function<String, Void> callback = (String twit) -> {
				log.info("twit received: " + twit);

				TwitDto twitDto = new TwitDto();
				twitDto.setMessage(twit);
				Message<TwitDto> message = MessageBuilder.withPayload(twitDto).setHeader(KafkaHeaders.TOPIC, topic).build();

				log.info("sending message to kafka: " + message.toString());
				kafkaTemplate.send(message);
				return null;
			};
			twitterService.read(10, callback, "kafka");
		}
		catch (Exception e)
		{
			log.error("Error when producing message to Kafka: " + e.getMessage(), e);
		}
	}

}
