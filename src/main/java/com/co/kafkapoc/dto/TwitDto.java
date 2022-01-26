package com.co.kafkapoc.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class TwitDto extends MessageDto
{
	private String id;
}
