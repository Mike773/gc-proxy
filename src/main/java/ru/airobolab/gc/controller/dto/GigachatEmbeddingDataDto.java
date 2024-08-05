package ru.airobolab.gc.controller.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GigachatEmbeddingDataDto {

    String object;
    List<Number> embedding;
    Long index;
    GigachatEmbeddingUsageDto usage;

}
