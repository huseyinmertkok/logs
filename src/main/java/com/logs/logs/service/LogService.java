package com.logs.logs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logs.logs.model.LogModel;
import com.logs.logs.repository.LogRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import search.SearchRequestDTO;
import search.util.SearchUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final RestHighLevelClient client;

    @Autowired
    public LogService(LogRepository logRepository, @Qualifier("elasticsearchClient") RestHighLevelClient client){
        this.logRepository = logRepository;
        this.client = client;
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

    public List<LogModel> search(final SearchRequestDTO dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                "log_index",
                dto
        );

        return searchInternal(request);
    }

    private List<LogModel> searchInternal(final SearchRequest request) {
        if (request == null) {
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            final SearchHit[] searchHits = response.getHits().getHits();
            final List<LogModel> logs = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                logs.add(
                        MAPPER.readValue(hit.getSourceAsString(), LogModel.class)
                );
            }

            return logs;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
