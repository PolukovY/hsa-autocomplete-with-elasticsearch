package com.levik.elasticsearch.cryptolions.service;

import com.levik.elasticsearch.cryptolions.CryptolionsClient;
import com.levik.elasticsearch.cryptolions.elasticsearch.ActionRepository;
import com.levik.elasticsearch.cryptolions.elasticsearch.model.DataAct;
import com.levik.elasticsearch.cryptolions.model.CryptoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class CryptolionsService {

    private final CryptolionsClient cryptolionsClient;
    private final ActionRepository actionRepository;

    public void populateData(){
        List<CryptoResponse.Action> actions = cryptolionsClient.getActions().getActions();

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
        //WildcardQueryBuilder search;
        MatchQueryBuilder search;

        if (Objects.nonNull(author)) {
            search = QueryBuilders.matchQuery("author", author);
            //search = QueryBuilders.wildcardQuery("author", author + "*");
            return actionRepository.search(search);
        }

        return Collections.emptyList();
    }


}
