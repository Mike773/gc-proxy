package ru.airobolab.gc.client.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponseDto {

    String access_token;
    Long expires_at;
}
