package ru.airobolab.gc.controller.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompletionsRequestDto {

    String model;

    List<GigachatCompletionsMessageDto> messages;
    Number temperature;
    Number top_p;
    Long n;
    Boolean stream;
    Long max_tokens;
    Number repetition_penalty;
    Number update_interval;

}
