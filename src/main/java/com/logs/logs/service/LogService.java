package com.logs.logs.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logs.logs.configuration.Config;
import com.logs.logs.model.LogModel;
import com.logs.logs.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.SearchRequestDTO;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class LogService {
    private final LogRepository logRepository;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final RestHighLevelClient client;

    @Autowired
    public LogService(LogRepository logRepository, Config config){
        this.logRepository = logRepository;
        this.client = config.elasticsearchClient();
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void save(final LogModel logModel){
        logRepository.save(logModel);
    }

    public List<LogModel> findAll(){
        List<LogModel> list = new ArrayList<>();
        Iterable<LogModel> findAll = logRepository.findAll();
        findAll.forEach(p -> list.add(p));
        return list;
    }

    public List<LogModel> search(final SearchRequestDTO dto) throws IOException {
        SearchRequest searchRequest = new SearchRequest("log_index");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(dto.getName() != null)
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("name",dto.getName()));
        if(dto.getStartDate() != null)
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.rangeQuery("createdAt").gte(dto.getStartDate()));
        if(dto.getEndDate() != null)
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.rangeQuery("createdAt").lte(dto.getEndDate()));
        if(dto.getGemNumber() != null)
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("id",dto.getGemNumber()));
        if(dto.getLevel() > 0)
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.rangeQuery("level").gte(dto.getLevel()));


        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        final SearchHit[] searchHits = searchResponse.getHits().getHits();
        final List<LogModel> logs = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            logs.add(
                    MAPPER.readValue(hit.getSourceAsString(), LogModel.class)
            );
        }
        logs.forEach(System.out::println);
        System.out.println(logs.size());
        return logs;
    }

}
