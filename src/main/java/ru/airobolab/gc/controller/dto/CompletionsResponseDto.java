package ru.airobolab.gc.controller.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompletionsResponseDto {
    List<GigachatCompletionsChoiceDto> choices;
    Long created;
    String model;
    GigachatCompletionsUsageDto usage;
    String object;
}
