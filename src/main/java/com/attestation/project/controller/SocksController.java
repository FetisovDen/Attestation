package com.attestation.project.controller;

import com.attestation.project.recordDto.SocksRecord;
import com.attestation.project.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/socks")
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @Operation(summary = "Регистрирует приход носков на склад",
            description = "Метод для регистрации нового прихода носков на склад",
            responses = {
                    @ApiResponse(description = "Удалось добавить приход",
                            responseCode = "200",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SocksRecord.class))),
                    @ApiResponse(description = "Параметры запроса отсутствуют или имеют некорректный формат",
                            responseCode = "400"),
                    @ApiResponse(description = "Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)",
                            responseCode = "500")})
    @PostMapping("/income")
    public ResponseEntity<String> income(@RequestBody SocksRecord socksRecord) {
        socksService.income(socksRecord);
        return ResponseEntity.status(HttpStatus.OK).body("Удалось добавить приход");
    }

    @Operation(summary = "Регистрирует отпуск носков со склада",
            description = "Метод для регистрации нового отпуска носков со склада",
            responses = {
                    @ApiResponse(description = "Удалось отпустить носки",
                            responseCode = "200",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SocksRecord.class))),
                    @ApiResponse(description = "Параметры запроса отсутствуют или имеют некорректный формат",
                            responseCode = "400"),
                    @ApiResponse(description = "Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)",
                            responseCode = "500")})
    @PostMapping("/outcome")
    public ResponseEntity<String> outcome(@RequestBody SocksRecord socksRecord) {
        socksService.outcome(socksRecord);
        return ResponseEntity.status(HttpStatus.OK).body("Удалось отпустить носки");
    }


    @Operation(summary = "Запросить информацию о количестве носков по параметрам",
            description = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса",
            responses = {
                    @ApiResponse(description = "Запрос выполнен",
                            responseCode = "200",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SocksRecord.class))),
                    @ApiResponse(description = "Параметры запроса отсутствуют или имеют некорректный формат",
                            responseCode = "400"),
                    @ApiResponse(description = "Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)",
                            responseCode = "500")})
    @GetMapping()
    private ResponseEntity<String> findCount(@RequestParam("color") String color,
                                                  @RequestParam("operation") String operation,
                                                  @Valid @RequestParam("cottonPart") @Min(0) @Max(100) int cottonPart) {
        return ResponseEntity.status(HttpStatus.OK).body(socksService.findCount(color,
                operation, cottonPart));
    }
}
