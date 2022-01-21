package com.fishingbooker.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableCaching(proxyTargetClass = true)
@EnableAsync(proxyTargetClass = true)
public class FishingBooker {

    public static void main(String[] args) {
        SpringApplication.run(FishingBooker.class, args);
    }

}
