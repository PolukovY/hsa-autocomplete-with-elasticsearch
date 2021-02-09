package com.levik.elasticsearch.cryptolions;

import com.levik.elasticsearch.cryptolions.model.CryptoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cryptolions", url = "https://wax.cryptolions.io/v2")
public interface CryptolionsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/history/get_actions?account=simpleassets&limit=100")
    CryptoResponse getActions();
}
