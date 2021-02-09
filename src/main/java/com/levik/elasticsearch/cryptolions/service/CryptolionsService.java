package com.levik.elasticsearch.cryptolions.service;

import com.levik.elasticsearch.cryptolions.CryptolionsClient;
import com.levik.elasticsearch.cryptolions.elasticsearch.ActionRepository;
import com.levik.elasticsearch.cryptolions.elasticsearch.model.DataAct;
import com.levik.elasticsearch.cryptolions.model.CryptoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CryptolionsService {

    public static final String CREATELOG = "createlog";

    private final CryptolionsClient cryptolionsClient;
    private final ActionRepository actionRepository;

    public void populateData(){
        CryptoResponse cryptoResponse = cryptolionsClient.getActions();
        List<CryptoResponse.Action> actions = cryptoResponse.getActions()
                .stream()
                .filter(it -> it.getAct() != null)
                .filter(it -> CREATELOG.equalsIgnoreCase(it.getAct().getName()))
                .collect(Collectors.toList());

        log.info("Get collected data from cryptolions actions size {}", actions.size());


        actions.stream()
                .filter(it -> it.getAct() != null && it.getAct().getData() != null)
                .forEach(it ->  {
                    CryptoResponse.Data data = it.getAct().getData();
                    DataAct dataAct = DataAct.toData(data);
                    actionRepository.save(dataAct);
                });

        log.info("Publish actions size {} to elasticsearch", actions.size());
    }


    public List<DataAct> getHistoryDataByAuthor(String author) {
        WildcardQueryBuilder search;

        if (Objects.nonNull(author)) {
            search = QueryBuilders.wildcardQuery("author", author + "*");
            return actionRepository.search(search);
        }

        return Collections.emptyList();
    }


}
