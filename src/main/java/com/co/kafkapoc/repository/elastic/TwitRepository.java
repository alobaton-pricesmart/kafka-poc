package com.co.kafkapoc.repository.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.co.kafkapoc.model.Twit;

public interface TwitRepository extends ElasticsearchRepository<Twit, String>
{

}
