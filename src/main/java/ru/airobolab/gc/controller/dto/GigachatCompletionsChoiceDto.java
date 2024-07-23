package ru.airobolab.gc.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GigachatCompletionsChoiceDto {

    GigachatCompletionsMessageDto message;
    Long index;
    String finish_reason;

}
