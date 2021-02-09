package com.levik.elasticsearch.cryptolions.elasticsearch.model;

import com.levik.elasticsearch.cryptolions.model.CryptoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setting(settingPath = "es-config/elastic-analyzer.json")
@Document(indexName = "data")
public class DataAct {
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String author;
    private String category;
    private String claimer;
    private String owner;
    private String idata;
    private String mdata;
    private boolean requireclaim;
    private String assetid;


    public static DataAct toData(CryptoResponse.Data data) {
        DataAct dataAct = new DataAct();

        dataAct.setId(UUID.randomUUID().toString());
        dataAct.setAuthor(data.getAuthor());
        dataAct.setCategory(data.getCategory());
        dataAct.setClaimer(data.getClaimer());
        dataAct.setIdata(data.getIdata());
        dataAct.setMdata(data.getMdata());
        dataAct.setRequireclaim(data.isRequireclaim());

        dataAct.setAssetid(data.getAssetid());

        return dataAct;
    }
}
