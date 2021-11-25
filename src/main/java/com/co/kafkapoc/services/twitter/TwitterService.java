package com.co.kafkapoc.services.twitter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.hbc.core.Client;

@Service
public class TwitterService {

	@Autowired
	private TwitterClientBuilder builder;

	public void read(int len, Function<String, Void> callback, String... terms) throws InterruptedException {
		BlockingQueue<String> queue = new LinkedBlockingQueue<>(len);
		Client client = createClient(queue, terms);
		client.connect();
		while (!client.isDone()) {
			String message = queue.take();
			callback.apply(message);
		}
		client.stop();
	}

	private Client createClient(BlockingQueue<String> queue, String... terms) {
		return createClient(queue, Arrays.asList(terms), Collections.emptyList());
	}

	private Client createClient(BlockingQueue<String> queue, List<String> terms, List<Long> followings) {
		String key = System.getenv("key");
		String secret = System.getenv("secret");
		String token = System.getenv("token");

		return builder.name("twitter-kafka-poc-01").key(key).secret(secret).token(token).terms(terms)
				.followings(followings).queue(queue).build();
	}

}
