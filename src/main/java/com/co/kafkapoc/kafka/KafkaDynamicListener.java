package com.co.kafkapoc.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

import com.co.kafkapoc.model.MessageDto;

public class KafkaDynamicListener implements MessageListener<String, MessageDto>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDynamicListener.class);

	@Override
	public void onMessage(ConsumerRecord<String, MessageDto> data)
	{
		if (data.value() == null) {
			LOGGER.info("value is null");
		}
		
		LOGGER.info(data.value().toString());
	}

}
