package ru.airobolab.gc.controller.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GigachatEmbeddingRequestDto {

    String model;
    List<String> input;
}
