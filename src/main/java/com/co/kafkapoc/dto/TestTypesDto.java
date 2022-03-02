package com.co.kafkapoc.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.confluent.kafka.schemaregistry.annotations.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(
		value = "{\"$schema\":\"http://json-schema.org/draft-07/schema#\",\"type\":\"object\",\"properties\":{\"stringNotRequired\":{\"type\":[\"string\",\"null\"]},\"stringRequired\":{\"type\":\"string\"},\"booleanNotRequired\":{\"type\":[\"boolean\",\"null\"]},\"booleanRequired\":{\"type\":\"boolean\"},\"numberNotRequired\":{\"type\":[\"number\",\"null\"]},\"numberRequired\":{\"type\":\"number\"},\"integerNotRequired\":{\"type\":[\"integer\",\"null\"]},\"integerRequired\":{\"type\":\"integer\"},\"arrayNotRequired\":{\"type\":[\"array\",\"null\"],\"items\":{\"type\":\"integer\"}},\"arrayRequired\":{\"type\":\"array\",\"items\":{\"type\":\"integer\"}},\"localDateTimeNotRequired\":{\"type\":[\"string\",\"null\"]},\"localDateTimeRequired\":{\"type\":\"string\"}},\"required\":[\"stringRequired\",\"booleanRequired\",\"numberRequired\",\"integerRequired\",\"arrayRequired\",\"localDateTimeRequired\"]}",
		refs = {})
@JsonPropertyOrder
@Data
@ToString
public class TestTypesDto implements Serializable
{
	private static final long serialVersionUID = -680996837223788859L;

	@JsonProperty(required = false)
	private String stringNotRequired;

	@JsonProperty(required = true)
	private String stringRequired;

	@JsonProperty(required = false)
	private Boolean booleanNotRequired;

	@JsonProperty(required = true)
	private Boolean booleanRequired;

	@JsonProperty(required = false)
	private Double numberNotRequired;

	@JsonProperty(required = true)
	private Double numberRequired;

	@JsonProperty(required = false)
	private Integer integerNotRequired;

	@JsonProperty(required = true)
	private Integer integerRequired;

	@JsonProperty(required = false)
	private List<Integer> arrayNotRequired;

	@JsonProperty(required = true)
	private List<Integer> arrayRequired;

	@JsonProperty(required = false)
	private LocalDateTime localDateTimeNotRequired;

	@JsonProperty(required = true)
	private LocalDateTime localDateTimeRequired;
}
