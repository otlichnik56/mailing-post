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
import ru.skypro.homework.entity.Mailing;
import ru.skypro.homework.entity.Post;
import ru.skypro.homework.service.MailingService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/mailing")
public class MailingController {

    private final Logger logger = LoggerFactory.getLogger(MailingController.class);
    private final MailingService mailingService;


    @Operation(summary = "Поиск всех почтовых отправлений",
            description = "нажмите на кнопку",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "найденные почтовые отправления",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Mailing.class))
                            )
                    )
            }
    )
    @GetMapping
    public List<Mailing> getAllMailing() {
        return mailingService.getAllMailing();
    }


    @Operation(summary = "Поиск почтового отправления по его уникальному ID",
            description = "введите ID почтового отправления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "найдено почтовое отправление",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Mailing.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "ничего не найдено"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getMailing(@PathVariable Integer id) {
        logger.info("MailingController. method getMailing. Mailing id = " + id);
        Mailing mailing = mailingService.getMailing(id);
        if (mailing == null) {
            return ResponseEntity.status(404).body("Mail item with this identifier was not found");
        } else {
            return ResponseEntity.status(200).body(mailing);
        }
    }


    @Operation(summary = "Добавление почтового отправления с его уникальным ID",
            description = "введите ID (можно и не вводить, система сама сгенерирует его), тип (можно цифрами от 0 до 3), индекс получателя, адрес и имя получателя почтового отправления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "почтовое отправление успешно сохранено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Mailing.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "почтовое отправление с таким ID уже существует"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<?> createMailing(@RequestBody Mailing mailing) {
        logger.info("MailingController. method createMailing. Mailing = " + mailing);
        if (mailingService.createMailing(mailing) == null) {
            return ResponseEntity.status(409).body("Perhaps a mailing list with this ID already exists or there is no post office with this index");
        } else {
            return ResponseEntity.status(200).body(mailing);
        }
    }


    @Operation(summary = "Изменение почтового отправления по его уникальному ID",
            description = "введите ID, тип (можно цифрами от 0 до 3), индекс получателя, адрес и имя получателя почтового отправления. почтовое отправление с таким ID должен существовать",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "почтовое отправление успешно изменено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Mailing.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "почтовое отправление с таким ID не существует"
                    )
            }
    )
    @PatchMapping
    public ResponseEntity<?> updateMailing(@RequestBody Mailing mailing) {
        logger.info("MailingController. method updateMailing. Mailing = " + mailing);
        if (mailingService.updateMailing(mailing) == null) {
            return ResponseEntity.status(404).body("Mail item with this identifier was not found");
        } else {
            return ResponseEntity.status(200).body(mailing);
        }
    }


    @Operation(summary = "Удаление почтового отправления по его уникальному ID",
            description = "введите ID почтового отправления для удаления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "почтовое отправление успешно удалено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Mailing.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "почтовое отправление с таким ID не существует"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMailing(@PathVariable Integer id) {
        logger.info("MailingController. method deleteMailing. Id = " + id);
        if (mailingService.deleteMailing(id)) {
            return ResponseEntity.status(200).body("Mail item deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Mail item with this identifier was not found");
        }
    }


}
