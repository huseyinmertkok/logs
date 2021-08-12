package com.logs.logs.repository;

import com.logs.logs.model.LogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends ElasticsearchRepository<LogModel, String> {
}
