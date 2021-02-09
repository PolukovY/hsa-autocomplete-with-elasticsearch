package com.levik.elasticsearch.cryptolions.scheduler;


import com.levik.elasticsearch.cryptolions.service.CryptolionsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class CryptolionsScheduler {

    private final CryptolionsService cryptolionsService;

    @Scheduled(fixedRate = 1000)
    public void run() {
        log.info("Run {}", LocalDateTime.now());
        cryptolionsService.populateData();
    }
}
