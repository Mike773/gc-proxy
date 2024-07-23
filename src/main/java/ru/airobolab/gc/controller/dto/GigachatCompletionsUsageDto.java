package ru.airobolab.gc.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GigachatCompletionsUsageDto {

    Long prompt_tokens;
    Long completion_tokens;
    Long total_tokens;
    Long system_tokens;

}
