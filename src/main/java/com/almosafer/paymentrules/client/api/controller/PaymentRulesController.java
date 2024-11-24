package com.almosafer.paymentrules.client.api.controller;

import com.almosafer.paymentrules.client.api.request.PaymentRuleRequest;
import com.almosafer.paymentrules.client.api.response.PaymentRuleResponse;
import com.almosafer.paymentrules.client.api.service.PaymentRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment-rules")
public class PaymentRulesController {
    private final PaymentRulesService paymentRulesService;

    @GetMapping
    public Mono<ResponseEntity<Flux<PaymentRuleResponse>>> getPaymentRules(
            @RequestParam(required = false) String paymentMethod,
            @RequestParam(required = false) Integer appId,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) String provider,
            @RequestParam(required = false) String scheme,
            @RequestParam(required = false) String localScheme) {
        PaymentRuleRequest paymentRuleRequest  = PaymentRuleRequest.builder()
                .paymentMethod(paymentMethod)
                .appId(appId)
                .currency(currency)
                .provider(provider)
                .scheme(scheme)
                .localScheme(localScheme)
                .build();
        Flux<PaymentRuleResponse> paymentRulesFlux = paymentRulesService.getPaymentRules(paymentRuleRequest);
        return paymentRulesFlux.hasElements()
                .flatMap(hasElements -> {
                    if(hasElements){
                        return Mono.just(ResponseEntity.ok(paymentRulesFlux));
                    }else{
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                });
    }
}
