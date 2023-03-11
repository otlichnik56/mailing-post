package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.TrackDto;
import ru.skypro.homework.entity.Track;
import ru.skypro.homework.service.TrackService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/track")
public class TrackController {

    private final Logger logger = LoggerFactory.getLogger(TrackController.class);
    private final TrackService trackService;


    @Operation(summary = "Регистрация почтового отправления. Необходимо указать ID отправления и индекс почтового отделения",
            description = "введите ID почтового отправления и индекс почтового отделения",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "передвижение почтового отправления успешно сохранено. возвращает только текстовое сообщение от сервиса независимо от результата",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Track.class)
                            )
                    )
            }
    )
    @PostMapping("/registered")
    public String registeredMailing(@RequestBody TrackDto trackDto) {
        logger.info("TrackController. method registeredMailing. TrackDto = " + trackDto);
        return trackService.registeredMailing(trackDto);
    }


    @Operation(summary = "Прибытие почтового отправления. Необходимо указать ID отправления и индекс почтового отделения",
            description = "введите ID почтового отправления и индекс почтового отделения",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "передвижение почтового отправления успешно сохранено. возвращает только текстовое сообщение от сервиса независимо от результата",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Track.class)
                            )
                    )
            }
    )
    @PostMapping("/arrive")
    public String arriveMailing(@RequestBody TrackDto trackDto) {
        logger.info("TrackController. method arriveMailing. TrackDto = " + trackDto);
        return trackService.arriveMailing(trackDto);
    }


    @Operation(summary = "Отправление почтового отправления. Необходимо указать ID отправления и индекс почтового отделения",
            description = "введите ID почтового отправления и индекс почтового отделения",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "передвижение почтового отправления успешно сохранено. возвращает только текстовое сообщение от сервиса независимо от результата",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Track.class)
                            )
                    )
            }
    )
    @PostMapping("/departed")
    public String departedMailing(@RequestBody TrackDto trackDto) {
        logger.info("TrackController. method departedMailing. TrackDto = " + trackDto);
        return trackService.departedMailing(trackDto);
    }


    @Operation(summary = "Отметка о получении почтового отправления. Необходимо указать ID отправления и индекс почтового отделения. Если индекс получателя не совпадёт с текущим отделением придёт ошибка",
            description = "введите ID почтового отправления и индекс почтового отделения",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "передвижение почтового отправления успешно сохранено. возвращает только текстовое сообщение от сервиса независимо от результата",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Track.class)
                            )
                    )
            }
    )
    @PostMapping("/received")
    public String receivedMailing(@RequestBody TrackDto trackDto) {
        logger.info("TrackController. method receivedMailing. TrackDto = " + trackDto);
        return trackService.receivedMailing(trackDto);
    }


    @Operation(summary = "Отслеживает всю историю передвижений почтового отправления. Необходимо указать ID отправления",
            description = "введите ID почтового отправления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "история передвижения почтового отправления",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Track.class))
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> trackHistoryMailing(@PathVariable Integer id) {
        logger.info("TrackController. method trackHistoryMailing. Mailing ID = " + id);
        List<String> history = trackService.trackHistoryMailing(id);
        if (history.isEmpty()) {
            return ResponseEntity.status(404).body("Mail item with this identifier was not found");
        } else {
            return ResponseEntity.status(200).body(history);
        }
    }

}
