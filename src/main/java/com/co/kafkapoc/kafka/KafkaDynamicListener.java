package com.co.kafkapoc.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

import com.fasterxml.jackson.databind.JsonNode;

public class KafkaDynamicListener implements MessageListener<String, JsonNode>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDynamicListener.class);

	@Override
	public void onMessage(ConsumerRecord<String, JsonNode> data)
	{
		if (data.value() == null)
		{
			LOGGER.info("empty message!");
		}
		else
		{
			LOGGER.info(data.value().toString());
		}
	}

}
