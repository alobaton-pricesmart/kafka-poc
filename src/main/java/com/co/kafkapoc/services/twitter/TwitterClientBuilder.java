package com.co.kafkapoc.services.twitter;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

@Component
@Validated
public class TwitterClientBuilder {

	private String name;
	private String key;
	private String secret;
	private String token;
	private List<Long> followings;
	private List<String> terms;
	private BlockingQueue<String> queue;
	
	public TwitterClientBuilder name(@NotNull @NotEmpty String name) {
		this.name = name;
		return this;
	}

	public TwitterClientBuilder key(@NotNull @NotEmpty String key) {
		this.key = key;
		return this;
	}

	public TwitterClientBuilder secret(@NotNull @NotEmpty String secret) {
		this.secret = secret;
		return this;
	}

	public TwitterClientBuilder token(@NotNull @NotEmpty String token) {
		this.token = token;
		return this;
	}

	public TwitterClientBuilder followings(@NotNull @NotEmpty List<Long> followings) {
		this.followings = followings;
		return this;
	}

	public TwitterClientBuilder terms(@NotNull @NotEmpty List<String> terms) {
		this.terms = terms;
		return this;
	}

	public TwitterClientBuilder queue(@NotNull BlockingQueue<String> queue) {
		this.queue = queue;
		return this;
	}
	
	public Client build() {
		Assert.notNull(name, "name cannot be null");
		Assert.notNull(key, "key cannot be null");
		Assert.notNull(secret, "secret cannot be null");
		Assert.notNull(token, "token cannot be null");
		Assert.notNull(queue, "token cannot be null");
		
		Hosts hosts = new HttpHosts(Constants.STREAM_HOST);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		endpoint.followings(followings);
		endpoint.trackTerms(terms);

		// These secrets should be read from a config file
		Authentication authentication = new OAuth1(key, secret, token, secret);

		ClientBuilder builder = new ClientBuilder().name(name) // optional: mainly for the logs
				.hosts(hosts).authentication(authentication).endpoint(endpoint)
				.processor(new StringDelimitedProcessor(queue));

		Client client = builder.build();

		return client;
	}

}
