package com.challenge.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO {

    private Long userId;
    private Long accelerationId;
    private Long companyId;
    private Integer status;
    private String createdAt;

}
