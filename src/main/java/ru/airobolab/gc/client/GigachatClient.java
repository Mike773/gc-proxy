package ru.airobolab.gc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.airobolab.gc.controller.dto.CompletionsRequestDto;
import ru.airobolab.gc.controller.dto.CompletionsResponseDto;
import ru.airobolab.gc.controller.dto.GigachatEmbeddingRequestDto;
import ru.airobolab.gc.controller.dto.GigachatEmbeddingResponseDto;

@FeignClient(name = "gigachat-service", url = "https://gigachat.devices.sberbank.ru/api")
public interface GigachatClient {
    @PostMapping("/v1/chat/completions")
    CompletionsResponseDto completions(
            @RequestBody CompletionsRequestDto query,
            @RequestHeader("Authorization") String token
    );

    @PostMapping("v1/embeddings")
    GigachatEmbeddingResponseDto embeddings(@RequestBody GigachatEmbeddingRequestDto query,@RequestHeader("Authorization") String token);
}
