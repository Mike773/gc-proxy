package ru.airobolab.gc.service;

import org.springframework.stereotype.Service;
import ru.airobolab.gc.client.AuthClient;
import ru.airobolab.gc.client.GigachatClient;
import ru.airobolab.gc.client.dto.TokenResponseDto;
import ru.airobolab.gc.configuration.MainConfig;
import ru.airobolab.gc.controller.dto.CompletionsRequestDto;
import ru.airobolab.gc.controller.dto.CompletionsResponseDto;
import ru.airobolab.gc.controller.dto.GigachatEmbeddingRequestDto;
import ru.airobolab.gc.controller.dto.GigachatEmbeddingResponseDto;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class GigachatService {

    private final AuthClient authClient;

    private final GigachatClient gigachatClient;
    private final MainConfig config;
    private Long tokenExpiredDate;
    private String authToken;

    private ExecutorService executorService;

    public GigachatService(AuthClient authClient, GigachatClient gigachatClient, MainConfig config){
        this.authClient = authClient;
        this.gigachatClient = gigachatClient;
        this.config = config;
        this.executorService = Executors.newSingleThreadExecutor();
//        TokenResponseDto token = authClient.oauth("scope=GIGACHAT_API_PERS","Basic: "+config.getAuth(), UUID.randomUUID());
//        this.tokenExpiredDate = token.getExpires_at();
    }

    public Long refreshToken(){
        if(authToken==null||tokenExpiredDate< (Instant.now().toEpochMilli())+600L){
            TokenResponseDto token = authClient.oauth("scope=GIGACHAT_API_PERS","Basic: "+config.getAuth(), UUID.randomUUID());
            tokenExpiredDate=token.getExpires_at();
            authToken=token.getAccess_token();
        }
        return tokenExpiredDate;
    }

    private String getToken(){
        refreshToken();
        return authToken;
    }

    public CompletionsResponseDto completions(CompletionsRequestDto completionsRequestDto){
        return gigachatClient.completions(completionsRequestDto,"Bearer "+getToken());
    }


    public CompletableFuture<CompletionsResponseDto> completionsAsync(CompletionsRequestDto completionsRequestDto){

        return CompletableFuture.supplyAsync(()-> completions(completionsRequestDto),executorService);
    }

    public CompletionsResponseDto getCompletions(CompletionsRequestDto completionsRequestDto) throws ExecutionException, InterruptedException {
        return completionsAsync(completionsRequestDto).get();
    }

    public GigachatEmbeddingResponseDto embeddings (GigachatEmbeddingRequestDto gigachatEmbeddingRequestDto){
        return gigachatClient.embeddings(gigachatEmbeddingRequestDto,"Bearer "+getToken());
    }

    public CompletableFuture<GigachatEmbeddingResponseDto> embeddingsAsync (GigachatEmbeddingRequestDto gigachatEmbeddingRequestDto){
        return CompletableFuture.supplyAsync(()-> embeddings(gigachatEmbeddingRequestDto),executorService);
    }


    public GigachatEmbeddingResponseDto getEmbeddings (GigachatEmbeddingRequestDto gigachatEmbeddingRequestDto) throws ExecutionException, InterruptedException {
        return embeddingsAsync(gigachatEmbeddingRequestDto).get();
    }
}
