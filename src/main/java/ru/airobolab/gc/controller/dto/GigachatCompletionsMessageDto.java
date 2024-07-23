package ru.airobolab.gc.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GigachatCompletionsMessageDto {
    String role;
    String content;
}
