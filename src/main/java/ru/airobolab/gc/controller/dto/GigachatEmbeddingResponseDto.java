package ru.airobolab.gc.controller.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GigachatEmbeddingResponseDto {

    String object;
    List<GigachatEmbeddingDataDto> data;
    String model;

}
