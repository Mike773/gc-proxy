package ru.airobolab.gc.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.airobolab.gc.client.dto.TokenRequestDto;
import ru.airobolab.gc.client.dto.TokenResponseDto;
import ru.airobolab.gc.controller.dto.CompletionsRequestDto;
import ru.airobolab.gc.controller.dto.CompletionsResponseDto;

import java.util.UUID;

@FeignClient(name = "auth-service", url = "https://ngw.devices.sberbank.ru:9443/api")
public interface AuthClient {

    //Mjk2MjRkZTEtNTAxYS00YzYxLTlhYzgtMTMxNThkODRjOTc0OjEzNzFiMTA0LTRhNzEtNDAxMi04YjgyLTY5NmNkMWM2Y2JmNQ==
    @PostMapping(value = "/v2/oauth", consumes = "application/x-www-form-urlencoded")
//    @Headers({
//            "Content-Type: application/x-www-form-urlencoded"})
    TokenResponseDto oauth(
            @RequestBody String scope,
            @RequestHeader("Authorization") String auth,
            @RequestHeader("RqUID") UUID rqUID
    );
}
