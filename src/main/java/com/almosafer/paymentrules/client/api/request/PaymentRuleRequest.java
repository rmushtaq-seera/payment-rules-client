package com.almosafer.paymentrules.client.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRuleRequest {
    String paymentMethod;
    Integer appId;
    String currency;
    String scheme;
    String localScheme;
    String provider;
}
