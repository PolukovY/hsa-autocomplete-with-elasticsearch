package com.levik.elasticsearch.cryptolions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CryptoResponse {
    @JsonProperty("last_irreversible_block")
    private String lastIrreversibleBlock;
    private List<Action> actions;

    @lombok.Data
    @NoArgsConstructor
    public static class Action {
        private Act act;
    }

    @lombok.Data
    @NoArgsConstructor
    public static class AccountTrace {
        @JsonProperty("trx_id")
        private String trxId;

    }

    @lombok.Data
    @NoArgsConstructor
    public static class Act {
        private String account;
        private String name;
        private Data data;
    }

    @lombok.Data
    @NoArgsConstructor
    public static class Data {
        private String author;
        private String category;
        private String claimer;
        private String owner;
        private String idata;
        private String mdata;
        private boolean requireclaim;
        private String assetid;

    }
}
