package com.eteration.simplebanking.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String status;
    private String approvalCode;
}
