package com.levik.elasticsearch.cryptolions.api;

import com.levik.elasticsearch.cryptolions.elasticsearch.model.DataAct;
import com.levik.elasticsearch.cryptolions.service.CryptolionsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/history")
public class CryptolionsController {

    private final CryptolionsService cryptolionsService;

    @GetMapping
    public List<DataAct> getHistoryData(@RequestParam String author) {
        return cryptolionsService.getHistoryDataByAuthor(author);
    }
}
