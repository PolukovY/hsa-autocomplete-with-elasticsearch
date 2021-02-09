package com.levik.elasticsearch.cryptolions.elasticsearch;

import com.levik.elasticsearch.cryptolions.elasticsearch.model.DataAct;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ActionRepository extends ElasticsearchRepository<DataAct, String> {

    List<DataAct> search(QueryBuilder query);
}
