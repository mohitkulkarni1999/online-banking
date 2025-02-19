package com.bank.authentication.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "card-service", url = "${card-service.url}")
public interface CardService {
}
