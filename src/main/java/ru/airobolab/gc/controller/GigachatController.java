package ru.airobolab.gc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.airobolab.gc.controller.dto.CompletionsRequestDto;
import ru.airobolab.gc.controller.dto.CompletionsResponseDto;
import ru.airobolab.gc.service.GigachatService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/chat")
@RequiredArgsConstructor
public class GigachatController {

    private final GigachatService gigachatService;
    @PostMapping("/completions")
    CompletionsResponseDto test(@RequestBody CompletionsRequestDto completionsRequestDto) throws ExecutionException, InterruptedException {
    return gigachatService.getCompletions(completionsRequestDto);
}

}
