package com.co.kafkapoc.kafka.dynamic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaDynamicListener implements MessageListener<String, JsonNode>
{
	@Override
	public void onMessage(ConsumerRecord<String, JsonNode> data)
	{
		if (data.value() == null)
		{
			log.info("empty message!");
		}
		else
		{
			log.info(data.value().toString());
		}
	}

}
