package com.co.kafkapoc.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

public class KafkaDynamicListener implements MessageListener<String, String>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDynamicListener.class);

	@Override
	public void onMessage(ConsumerRecord<String, String> data)
	{
		LOGGER.info(data.value());
	}

}
