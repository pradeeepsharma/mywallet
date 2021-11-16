package com.learning.microservice.mywallet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WalletService {
    @Value("${service.uri.wallet}")
    private String walletServiceUri;

    public void createWallet(Long id) {
        Boolean walletCreated = WebClient.create()
                .post()
                .uri(walletServiceUri + id)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

}
