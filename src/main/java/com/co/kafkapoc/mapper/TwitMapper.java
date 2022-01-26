package com.co.kafkapoc.mapper;

import org.mapstruct.Mapper;

import com.co.kafkapoc.dto.TwitDto;
import com.co.kafkapoc.model.Twit;

// componentModel allows mapping with dependency injection
@Mapper(componentModel = "spring")
public interface TwitMapper
{
	TwitDto entityToDto(Twit entity);

	Twit dtoToEneity(TwitDto dto);

}
