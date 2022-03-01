package com.co.kafkapoc.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.co.kafkapoc.util.KafkaJsonSchemaJacksonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TestTypesDto implements Serializable
{
	/**
	 * 
	 */
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
	@JsonSerialize(using = KafkaJsonSchemaJacksonUtil.LocalDateTimeSerializer.class)
	@JsonDeserialize(using = KafkaJsonSchemaJacksonUtil.LocalDateTimeDeserializer.class)
	private LocalDateTime localDateTimeNotRequired;

	@JsonProperty(required = true)
	@JsonSerialize(using = KafkaJsonSchemaJacksonUtil.LocalDateTimeSerializer.class)
	@JsonDeserialize(using = KafkaJsonSchemaJacksonUtil.LocalDateTimeDeserializer.class)
	private LocalDateTime localDateTimeRequired;

	@JsonProperty(required = false)
	@JsonSerialize(using = KafkaJsonSchemaJacksonUtil.LocalDateSerializer.class)
	@JsonDeserialize(using = KafkaJsonSchemaJacksonUtil.LocalDateDeserializer.class)
	private LocalDate localDateNotRequired;

	@JsonProperty(required = true)
	@JsonSerialize(using = KafkaJsonSchemaJacksonUtil.LocalDateSerializer.class)
	@JsonDeserialize(using = KafkaJsonSchemaJacksonUtil.LocalDateDeserializer.class)
	private LocalDate localDateRequired;

	@JsonProperty(required = false)
	@JsonSerialize(using = KafkaJsonSchemaJacksonUtil.LocalDateTimeSerializer.class)
	@JsonDeserialize(using = KafkaJsonSchemaJacksonUtil.LocalDateTimeDeserializer.class)
	private LocalTime localTimeNotRequired;

	@JsonProperty(required = true)
	@JsonSerialize(using = KafkaJsonSchemaJacksonUtil.LocalTimeSerializer.class)
	@JsonDeserialize(using = KafkaJsonSchemaJacksonUtil.LocalTimeDeserializer.class)
	private LocalTime localTimeRequired;

}
