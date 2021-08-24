package com.logs.logs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logs.logs.configuration.Config;
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
    public LogService(LogRepository logRepository, Config config){
        this.logRepository = logRepository;
        this.client = config.elasticsearchClient();
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
            System.out.println("Request:");
            System.out.println(request);
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            System.out.println("Response:");
            System.out.println(response);
            final SearchHit[] searchHits = response.getHits().getHits();
            System.out.println("Search Hit:");
            System.out.println(searchHits[0]);
            final List<LogModel> logs = new ArrayList<>();
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
