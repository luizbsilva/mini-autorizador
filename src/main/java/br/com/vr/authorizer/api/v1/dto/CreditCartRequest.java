package br.com.vr.authorizer.api.v1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCartRequest {
    private String numberCard;
    private String password;

}
