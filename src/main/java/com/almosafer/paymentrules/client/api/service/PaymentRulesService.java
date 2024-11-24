package com.almosafer.paymentrules.client.api.service;

import com.almosafer.paymentrules.client.api.request.PaymentRuleRequest;
import com.almosafer.paymentrules.client.api.response.PaymentRuleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentRulesService {
    private final WebClient webClient;
    public Flux<PaymentRuleResponse> getPaymentRules(PaymentRuleRequest paymentRuleRequest){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParamIfPresent("paymentMethod", Optional.ofNullable(paymentRuleRequest.getPaymentMethod()))
                        .queryParamIfPresent("currency", Optional.ofNullable(paymentRuleRequest.getCurrency()))
                        .queryParamIfPresent("appId", Optional.ofNullable(paymentRuleRequest.getAppId()))
                        .queryParamIfPresent("provider", Optional.ofNullable(paymentRuleRequest.getProvider()))
                        .queryParamIfPresent("scheme", Optional.ofNullable(paymentRuleRequest.getScheme()))
                        .queryParamIfPresent("localScheme", Optional.ofNullable(paymentRuleRequest.getLocalScheme()))
                        .build()
                )
                .retrieve().
                bodyToFlux(PaymentRuleResponse.class);
    }
}
