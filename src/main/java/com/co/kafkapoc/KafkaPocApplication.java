package com.co.kafkapoc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.co.kafkapoc.kafka.twitter.TwitProducer;

@SpringBootApplication
public class KafkaPocApplication
{
	
	@Autowired
	private TwitProducer twitProducer;

	public static void main(String[] args)
	{
		SpringApplication.run(KafkaPocApplication.class, args);
	}

	@PostConstruct
	void postConstruct()
	{
		twitProducer.produce();

	}

}
