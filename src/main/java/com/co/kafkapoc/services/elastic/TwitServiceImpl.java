package com.co.kafkapoc.services.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kafkapoc.model.Twit;
import com.co.kafkapoc.repository.elastic.TwitRepository;

@Service
public class TwitServiceImpl implements TwitService
{

	@Autowired
	private TwitRepository repository;

	@Override
	public Twit save(Twit entity)
	{
		return repository.save(entity);
	}

}
