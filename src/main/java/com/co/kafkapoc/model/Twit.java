package com.co.kafkapoc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "twit")
@Data
public class Twit
{
	@Id
	private String id;

	private String message;
}
